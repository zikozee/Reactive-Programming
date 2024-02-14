package com.rp.sec11sink;

import com.rp.courseutil.Util;
import com.rp.sec11sink.assignment.SlackMember;
import com.rp.sec11sink.assignment.SlackRoom;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 14 Feb, 2024
 */

public class Lec07SlackDemo {

    public static void main(String[] args) {

        SlackRoom slackRoom = new SlackRoom("reactor");

        SlackMember sam = new SlackMember("sam");
        SlackMember jake = new SlackMember("jake");
        SlackMember mike = new SlackMember("mike");


        slackRoom.joinRoom(sam);
        slackRoom.joinRoom(jake);

        sam.says("Hi all..");

        Util.sleepSecond(4);

        jake.says("Hey!");
        sam.says("I simple wanted to say Hi...");

        Util.sleepSecond(4);

        slackRoom.joinRoom(mike);
        mike.says("Hey Guys.. glad to be here...");



    }
}
