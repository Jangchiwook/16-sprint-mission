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

import java.util.UUID;

public class JavaApplication {

    public static void main(String[] args) {

        UserService userService = new JCFUserService();
        ChannelService channelService = new JCFChannelService();

        MessageService messageService =
                new JCFMessageService(userService, channelService);


        System.out.println("========== 사용자 CRUD ==========");

        User user = userService.create(
                "장치욱",
                "spsb7319@naver.com",
                "7319"
        );

        System.out.println("[사용자 생성]");
        System.out.println("사용자 이름: " + user.getUsername());
        System.out.println("사용자 ID: " + user.getId());


        User foundUser = userService.findById(user.getId());

        System.out.println("[사용자 단건 조회]");
        printUser(foundUser);


        System.out.println("[사용자 전체 조회]");

        for (User u : userService.findAll()) {
            printUser(u);
        }


        userService.update(
                user.getId(),
                "장시우",
                "spsb7937@naver.com",
                "7937"
        );

        System.out.println("[사용자 수정 후 조회]");

        User updatedUser = userService.findById(user.getId());
        printUser(updatedUser);


        System.out.println("========== 채널 CRUD ==========");

        Channel channel = channelService.create(
                "일반 채팅",
                "자유롭게 대화하는 채널"
        );

        System.out.println("[채널 생성]");
        System.out.println("채널 이름: " + channel.getName());
        System.out.println("채널 ID: " + channel.getId());


        Channel foundChannel = channelService.findById(channel.getId());

        System.out.println("[채널 단건 조회]");
        printChannel(foundChannel);


        System.out.println("[채널 전체 조회]");

        for (Channel c : channelService.findAll()) {
            printChannel(c);
        }


        channelService.update(
                channel.getId(),
                "공지 채널",
                "공지사항을 확인하는 채널"
        );

        System.out.println("[채널 수정 후 조회]");

        Channel updatedChannel = channelService.findById(channel.getId());
        printChannel(updatedChannel);


        System.out.println("========== 메시지 CRUD ==========");

        Message message = messageService.create(
                "안녕하세요",
                user.getId(),
                channel.getId()
        );

        System.out.println("[메시지 생성]");
        System.out.println("메시지 ID: " + message.getId());
        System.out.println("메시지 내용: " + message.getContent());


        Message foundMessage = messageService.findById(message.getId());

        System.out.println("[메시지 단건 조회]");
        printMessage(foundMessage);


        System.out.println("[메시지 전체 조회]");

        for (Message m : messageService.findAll()) {
            printMessage(m);
        }


        messageService.update(
                message.getId(),
                "반갑습니다"
        );

        System.out.println("[메시지 수정 후 조회]");

        Message updatedMessage = messageService.findById(message.getId());
        printMessage(updatedMessage);


        System.out.println("========== 삭제 테스트 ==========");

        System.out.println("[메시지 삭제]");
        System.out.println("삭제할 메시지 ID: " + message.getId());

        messageService.delete(message.getId());

        Message deletedMessage = messageService.findById(message.getId());

        if (deletedMessage == null) {
            System.out.println("메시지가 정상적으로 삭제되었습니다.");
        } else {
            System.out.println("메시지 삭제에 실패했습니다.");
        }


        System.out.println("[사용자 삭제]");
        System.out.println("삭제할 사용자 ID: " + user.getId());

        userService.delete(user.getId());

        User deletedUser = userService.findById(user.getId());

        if (deletedUser == null) {
            System.out.println("사용자가 정상적으로 삭제되었습니다.");
        } else {
            System.out.println("사용자 삭제에 실패했습니다.");
        }


        System.out.println("[채널 삭제]");
        System.out.println("삭제할 채널 ID: " + channel.getId());

        channelService.delete(channel.getId());

        Channel deletedChannel = channelService.findById(channel.getId());

        if (deletedChannel == null) {
            System.out.println("채널이 정상적으로 삭제되었습니다.");
        } else {
            System.out.println("채널 삭제에 실패했습니다.");
        }
    }


    private static void printUser(User user) {

        if (user == null) {
            System.out.println("사용자를 찾을 수 없습니다.");
            return;
        }

        System.out.println("ID: " + user.getId());
        System.out.println("이름: " + user.getUsername());
        System.out.println("이메일: " + user.getEmail());
        System.out.println("생성 시간: " + user.getCreatedAt());
        System.out.println("수정 시간: " + user.getUpdatedAt());
        System.out.println("------------------------------");
    }


    private static void printChannel(Channel channel) {

        if (channel == null) {
            System.out.println("채널을 찾을 수 없습니다.");
            return;
        }

        System.out.println("ID: " + channel.getId());
        System.out.println("채널 이름: " + channel.getName());
        System.out.println("채널 설명: " + channel.getDescription());
        System.out.println("생성 시간: " + channel.getCreatedAt());
        System.out.println("수정 시간: " + channel.getUpdatedAt());
        System.out.println("------------------------------");
    }


    private static void printMessage(Message message) {

        if (message == null) {
            System.out.println("메시지를 찾을 수 없습니다.");
            return;
        }

        System.out.println("ID: " + message.getId());
        System.out.println("메시지 내용: " + message.getContent());
        System.out.println("작성자 ID: " + message.getUserId());
        System.out.println("채널 ID: " + message.getChannelId());
        System.out.println("생성 시간: " + message.getCreatedAt());
        System.out.println("수정 시간: " + message.getUpdatedAt());
        System.out.println("------------------------------");
    }
}
