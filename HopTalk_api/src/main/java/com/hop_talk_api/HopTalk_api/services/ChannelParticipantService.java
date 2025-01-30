package com.hop_talk_api.HopTalk_api.services;

import com.hop_talk_api.HopTalk_api.constants.ChannelParticipantRole;
import com.hop_talk_api.HopTalk_api.entities.ChannelParticipant;
import com.hop_talk_api.HopTalk_api.entities.GroupChannel;
import com.hop_talk_api.HopTalk_api.entities.User;
import com.hop_talk_api.HopTalk_api.repositories.ChannelParticipantRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelParticipantService {

    private ChannelParticipantRepo channelParticipantRepository;

    public ChannelParticipantService(ChannelParticipantRepo channelParticipantRepo){
        this.channelParticipantRepository = channelParticipantRepo;
    }

    public ChannelParticipant AddOwnerToChannelParticipants(GroupChannel groupChannel){
        ChannelParticipant participant = new ChannelParticipant();

        participant.setRole(ChannelParticipantRole.OWNER);
        participant.setGroupChannel(groupChannel);
        participant.setUser(groupChannel.getCreator());

        this.channelParticipantRepository.save(participant);

        return participant;
    }

    public ChannelParticipant AddParticipantToChannelParticipants(GroupChannel groupChannel, User userToAdd, int userWithPermission){
        ChannelParticipant participant = new ChannelParticipant();

        if(this.channelParticipantRepository.findByGroupChannelIdAndUserIdAndIsActiveTrue(groupChannel.getId(), userToAdd.getId()) != null){
            throw new IllegalArgumentException("The user is already a participant in the channel.");
        }

        if(hasPermissionForAdminAndOwner(groupChannel.getId(), userWithPermission)){
            participant.setGroupChannel(groupChannel);
            participant.setUser(userToAdd);

            channelParticipantRepository.save(participant);
        }

        return participant;
    }

    public boolean removeParticipantFromChannel(int userId, int channelId, int participantId){
        ChannelParticipant participantToRemove = this.channelParticipantRepository
                .findByGroupChannelIdAndUserIdAndIsActiveTrue(channelId,participantId);

        if(hasPermissionForAdminAndOwner(channelId, userId) && participantToRemove != null){
            participantToRemove.setActive(false);
            this.channelParticipantRepository.save(participantToRemove);
            return true;
        }

        return false;
    }

    public ChannelParticipant adminPermissionSetter(int channelId, int userId){
        ChannelParticipant participant = this.channelParticipantRepository.findByGroupChannelIdAndUserIdAndIsActiveTrue(channelId, userId);
        if(participant != null){
            participant.setRole(ChannelParticipantRole.ADMIN);
            this.channelParticipantRepository.save(participant);

            return participant;
        }

        throw new RuntimeException("Such participant or channel didn't exist");
    }

    public boolean hasPermissionForOwner(int channelId, int userId){
        ChannelParticipant participant = this.channelParticipantRepository
                .findByGroupChannelIdAndUserIdAndIsActiveTrue(channelId, userId);

        if(participant == null){
            throw new RuntimeException("There is no such active channel-participant record");
        }

        return participant.getRole() == ChannelParticipantRole.OWNER;
    }

    public boolean hasPermissionForAdminAndOwner(int channelId, int userId){
        ChannelParticipant participant = this.channelParticipantRepository
                .findByGroupChannelIdAndUserIdAndIsActiveTrue(channelId, userId);

        if(participant == null){
            throw new RuntimeException("There is no such active channel-participant record");
        }

        return participant.getRole() == ChannelParticipantRole.OWNER || participant.getRole() == ChannelParticipantRole.ADMIN;
    }

    public List<GroupChannel> getAllGroupsByUserId(int userId){
        return this.channelParticipantRepository.findGroupChannelByUserId(userId);
    }
}
