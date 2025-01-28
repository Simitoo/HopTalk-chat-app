package com.hop_talk_api.HopTalk_api.services;

import com.hop_talk_api.HopTalk_api.dto.DirectChannelDTO;
import com.hop_talk_api.HopTalk_api.entities.DirectChannel;
import com.hop_talk_api.HopTalk_api.repositories.DirectChannelRepo;
import com.hop_talk_api.HopTalk_api.repositories.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectChannelService {

    private final DirectChannelRepo directChannelRepository;
    private final UserRepo userRepository;

    public DirectChannelService(DirectChannelRepo directChannelRepo, UserRepo userRepository){
        this.directChannelRepository = directChannelRepo;
        this.userRepository = userRepository;
    }

    public boolean createDirectChannel(String userUsername, String participantUsername){
        DirectChannel newDirectChannel = new DirectChannel();

        if(userUsername.isEmpty() && participantUsername.isEmpty()){
            return false;
        }

        newDirectChannel.setUser(this.userRepository.findUserByUsername(userUsername));
        newDirectChannel.setParticipant(this.userRepository.findUserByUsername(participantUsername));

        this.directChannelRepository.save(newDirectChannel);
        return true;
    }

    public List<DirectChannelDTO> fetchAllForCurrUser(String username){
        return this.directChannelRepository.findAllForUserAndChannelIsActive(username, true)
                .stream()
                .map(this::createDirectChannelDto)
                .toList();
    }

    public boolean removeDirectChannel(int directChannelId){
        return false;
    }

    private DirectChannelDTO createDirectChannelDto(DirectChannel directChannel){
        return new DirectChannelDTO(directChannel, directChannel.getUser());
    }
}
