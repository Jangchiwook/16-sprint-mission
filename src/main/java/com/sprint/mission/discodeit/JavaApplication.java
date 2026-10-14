package com.sprint.mission.discodeit;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.Message;

import com.sprint.mission.discodeit.service.UserService;
import com.sprint.mission.discodeit.service.ChannelService;
import com.sprint.mission.discodeit.service.MessageService;

import com.sprint.mission.discodeit.service.jcf.JCFUserService;
import com.sprint.mission.discodeit.service.jcf.JCFChannelService;
import com.sprint.mission.discodeit.service.jcf.JCFMessageService;

public class JavaApplication {

    public static void main(String[] args) {

        UserService userService = new JCFUserService();
        ChannelService channelService = new JCFChannelService(userService);
        MessageService messageService = new JCFMessageService(userService, channelService);

        System.out.println("사용자 CRUD");

        User user = userService.create("장치욱", "spsb7319@naver.com", "7319");

        System.out.println("사용자 생성: " + user.getUsername());
        System.out.println("사용자 ID: " + user.getId());

        User foundUser = userService.findById(user.getId());

        System.out.println("사용자 단건 조회");
        printUser(foundUser);

        System.out.println("사용자 전체 조회");

        for (User u : userService.findAll()) {
            printUser(u);
        }

        User updatedUser = userService.update(
                user.getId(),
                "장시우",
                "spsb7937@naver.com",
                "7937"
        );

        System.out.println("사용자 수정 후 조회");
        printUser(updatedUser);

        System.out.println("채널 CRUD");

        Channel channel = channelService.create(
                "일반 채팅",
                "자유롭게 대화하는 채널"
        );

        System.out.println("채널 생성: " + channel.getName());
        System.out.println("채널 ID: " + channel.getId());

        Channel foundChannel = channelService.findById(channel.getId());

        System.out.println("채널 단건 조회");
        printChannel(foundChannel);

        System.out.println("채널 전체 조회");

        for (Channel c : channelService.findAll()) {
            printChannel(c);
        }

        Channel updatedChannel = channelService.update(
                channel.getId(),
                "공지 채널",
                "공지사항을 확인하는 채널"
        );

        System.out.println("채널 수정 후 조회");
        printChannel(updatedChannel);

        System.out.println("채널 참여");

        channelService.joinChannel(channel.getId(), user.getId());

        System.out.println("참여 사용자: " + user.getUsername());
        System.out.println("참여 채널: " + channel.getName());

        System.out.println("사용자와 채널 관계 조회");
        printUser(userService.findById(user.getId()));
        printChannel(channelService.findById(channel.getId()));

        System.out.println("메시지 CRUD");

        Message message = messageService.create(
                "안녕하세요!",
                user.getId(),
                channel.getId()
        );

        System.out.println("메시지 생성: " + message.getContent());
        System.out.println("메시지 ID: " + message.getId());

        Message foundMessage = messageService.findById(message.getId());

        System.out.println("메시지 단건 조회");
        printMessage(foundMessage);

        System.out.println("메시지 전체 조회");

        for (Message m : messageService.findAll()) {
            printMessage(m);
        }

        Message updatedMessage = messageService.update(
                message.getId(),
                "반갑습니다!"
        );

        System.out.println("메시지 수정 후 조회");
        printMessage(updatedMessage);

        System.out.println("메시지 작성 후 관계 조회");
        printUser(userService.findById(user.getId()));
        printChannel(channelService.findById(channel.getId()));

        System.out.println("메시지 삭제");
        System.out.println("삭제할 메시지 ID: " + message.getId());

        messageService.delete(message.getId());

        try {
            messageService.findById(message.getId());
            System.out.println("메시지 삭제 실패");
        } catch (IllegalArgumentException e) {
            System.out.println("메시지가 정상적으로 삭제되었습니다.");
        }

        System.out.println("메시지 삭제 후 관계 조회");
        printUser(userService.findById(user.getId()));
        printChannel(channelService.findById(channel.getId()));

        System.out.println("채널 탈퇴");

        channelService.leaveChannel(channel.getId(), user.getId());

        System.out.println("탈퇴 사용자: " + user.getUsername());
        System.out.println("탈퇴 채널: " + channel.getName());

        System.out.println("채널 탈퇴 후 관계 조회");
        printUser(userService.findById(user.getId()));
        printChannel(channelService.findById(channel.getId()));

        System.out.println("사용자 삭제");
        System.out.println("삭제할 사용자 ID: " + user.getId());

        userService.delete(user.getId());

        try {
            userService.findById(user.getId());
            System.out.println("사용자 삭제 실패");
        } catch (IllegalArgumentException e) {
            System.out.println("사용자가 정상적으로 삭제되었습니다.");
        }

        System.out.println("채널 삭제");
        System.out.println("삭제할 채널 ID: " + channel.getId());

        channelService.delete(channel.getId());

        try {
            channelService.findById(channel.getId());
            System.out.println("채널 삭제 실패");
        } catch (IllegalArgumentException e) {
            System.out.println("채널이 정상적으로 삭제되었습니다.");
        }
    }

    private static void printUser(User user) {

        System.out.println("ID: " + user.getId());
        System.out.println("이름: " + user.getUsername());
        System.out.println("이메일: " + user.getEmail());
        System.out.println("참여 채널 ID: " + user.getChannelIds());
        System.out.println("작성 메시지 ID: " + user.getMessageIds());
        System.out.println("생성 시간: " + user.getCreatedAt());
        System.out.println("수정 시간: " + user.getUpdatedAt());
    }

    private static void printChannel(Channel channel) {

        System.out.println("ID: " + channel.getId());
        System.out.println("채널 이름: " + channel.getName());
        System.out.println("채널 설명: " + channel.getDescription());
        System.out.println("참여 사용자 ID: " + channel.getMemberIds());
        System.out.println("메시지 ID: " + channel.getMessageIds());
        System.out.println("생성 시간: " + channel.getCreatedAt());
        System.out.println("수정 시간: " + channel.getUpdatedAt());
    }

    private static void printMessage(Message message) {

        System.out.println("ID: " + message.getId());
        System.out.println("메시지 내용: " + message.getContent());
        System.out.println("작성자 ID: " + message.getUserId());
        System.out.println("채널 ID: " + message.getChannelId());
        System.out.println("생성 시간: " + message.getCreatedAt());
        System.out.println("수정 시간: " + message.getUpdatedAt());
    }
}
