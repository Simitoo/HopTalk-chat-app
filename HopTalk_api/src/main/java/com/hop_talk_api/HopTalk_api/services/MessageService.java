package com.hop_talk_api.HopTalk_api.services;

import com.hop_talk_api.HopTalk_api.dto.MessageDTO;
import com.hop_talk_api.HopTalk_api.entities.Message;
import com.hop_talk_api.HopTalk_api.repositories.MessageRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class MessageService {

    private final MessageRepo messageRepository;

    public MessageService(MessageRepo messageRepository){
        this.messageRepository = messageRepository;
    }

    public boolean createMessage(Message newMessage){
        this.messageRepository.save(newMessage);
        return true;
    }

    public List<MessageDTO> fetchAllForDirectChannel(int receiverId){
        Pageable pageable = PageRequest.of(0,20);
        return this.messageRepository.findAllForDirectChannel(receiverId,pageable)
                .stream()
                .map(this::createMessageDTO)
                .toList();
    }

    public List<MessageDTO> fetchAllForGroupChannel(int receiverId){
        Pageable pageable = PageRequest.of(0,20);
        return this.messageRepository.findAllForGroupChannel(receiverId,pageable)
                .stream()
                .map(this::createMessageDTO)
                .toList();
    }

    public boolean deleteMessage(int id){
        Message messageToRemove = this.messageRepository.findMessageById(id);
        if(messageToRemove != null){
            messageToRemove.setActive(false);
            this.messageRepository.save(messageToRemove);
            return true;
        }

        return false;
    }

    private MessageDTO createMessageDTO(Message message){
        return new MessageDTO(
                message.getId(),
                message.getSender().getUsername(),
                message.getMessage(),
                message.getMessageType(),
                message.getAttachmentUrl(),
                message.getCreatedAt()
        );
    }
}
