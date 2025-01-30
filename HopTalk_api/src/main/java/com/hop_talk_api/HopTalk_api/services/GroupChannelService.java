package com.hop_talk_api.HopTalk_api.services;

import com.hop_talk_api.HopTalk_api.dto.BasicGroupChannelDTO;
import com.hop_talk_api.HopTalk_api.dto.GroupChannelDTO;
import com.hop_talk_api.HopTalk_api.dto.GroupParticipantDTO;
import com.hop_talk_api.HopTalk_api.entities.ChannelParticipant;
import com.hop_talk_api.HopTalk_api.entities.GroupChannel;
import com.hop_talk_api.HopTalk_api.entities.User;
import com.hop_talk_api.HopTalk_api.repositories.GroupChannelRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupChannelService {

    private GroupChannelRepo groupChannelRepository;
    private UserService userService;
    private ChannelParticipantService channelParticipantService;

    public GroupChannelService(GroupChannelRepo groupChannelRepo, UserService userService, ChannelParticipantService participantService){
        this.groupChannelRepository = groupChannelRepo;
        this.userService = userService;
        this.channelParticipantService = participantService;
    }

    public boolean addGroupChannel(GroupChannelDTO groupChannel){
        GroupChannel newChannel = createGroupChannel(groupChannel);

        ChannelParticipant participant = this.channelParticipantService.AddOwnerToChannelParticipants(newChannel);
        newChannel.addParticipant(participant);
        return true;
    }

    public List<GroupChannelDTO> fetchAllGroupsByUserId(int userId){

        return this.channelParticipantService.getAllGroupsByUserId(userId)
                .stream().map(this::createGroupDto)
                .toList();
    }

    public boolean addParticipant(int userId, int channelId, int participantId){
        GroupChannel channel = this.groupChannelRepository.findByIdWithParticipants(channelId);
        User participantToAdd = this.userService.getUserById(participantId);

        if(channel != null && participantToAdd != null){
            ChannelParticipant channelParticipant = this.channelParticipantService.AddParticipantToChannelParticipants(channel, participantToAdd, userId);
            if(channelParticipant != null){
                channel.addParticipant(channelParticipant);
                this.groupChannelRepository.save(channel);
                return true;
            }
        }
        else {
            throw new RuntimeException("There is no such Group channel or User");
        }

        return false;
    }

    public boolean removeParticipant(int userId, int channelId, int participantId){
        return this.channelParticipantService.removeParticipantFromChannel(userId, channelId, participantId);
    }

    public boolean giveParticipantAdminRole(int channelId, int userId, int participantToChangeId){
        if(this.channelParticipantService.hasPermissionForOwner(channelId, userId)){
            ChannelParticipant adminParticipant = this.channelParticipantService.adminPermissionSetter(channelId, participantToChangeId);
            GroupChannel groupChannel = this.groupChannelRepository.findByIdWithParticipants(channelId);

            if(groupChannel != null && adminParticipant != null){
                groupChannel.getParticipants().removeIf(p -> p.getId() == adminParticipant.getId());
                groupChannel.addParticipant(adminParticipant);

                this.groupChannelRepository.save(groupChannel);
                return true;
            }
        }

        return false;
    }

    public boolean updateGroupChannel(int userId, int channelId, BasicGroupChannelDTO newGroupData){

        if(this.channelParticipantService.hasPermissionForAdminAndOwner(channelId, userId)){
            GroupChannel channelToUpdate = this.groupChannelRepository.findById(channelId);

            channelToUpdate.setTitle(newGroupData.getTitle());
            channelToUpdate.setIconUrl(newGroupData.getIconUrl());

            this.groupChannelRepository.save(channelToUpdate);
            return true;
        }

        return false;
    }

    public boolean removeGroupChannel(int channelId, int userId){
        GroupChannel channelToRemove = this.groupChannelRepository.findById(channelId);

        if(this.channelParticipantService.hasPermissionForOwner(channelId, userId) && channelToRemove != null){
            channelToRemove.setActive(false);
            this.groupChannelRepository.save(channelToRemove);
            return true;
        }

        return false;
    }

    private GroupChannel createGroupChannel(GroupChannelDTO channelDTO){
        User creator = this.userService.getUserById(channelDTO.getCreatorId());
        GroupChannel groupChannel = new GroupChannel();
        groupChannel.setId(channelDTO.getId());
        groupChannel.setTitle(channelDTO.getTitle());
        groupChannel.setIconUrl(channelDTO.getIconUrl());
        groupChannel.setCreator(creator);

        this.groupChannelRepository.save(groupChannel);

        return groupChannel;
    }

    private GroupChannelDTO createGroupDto(GroupChannel channel){
        return new GroupChannelDTO(channel.getId(), channel.getTitle(), channel.getCreator().getId(), channel.getIconUrl(), channel.getParticipants().stream()
                .map(participant -> new GroupParticipantDTO(participant.getId(), participant.getUser().getUsername(), participant.getUser().getIconUrl(), participant.getRole()))
                .toList());
    }

}
