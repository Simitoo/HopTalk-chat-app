package com.hop_talk_api.HopTalk_api.constants;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum ChannelParticipantRole {
    OWNER,
    ADMIN,
    PARTICIPANT,
}
