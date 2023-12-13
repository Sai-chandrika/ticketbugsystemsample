package com.example.ticket_bug_system.service.impl;

import com.example.ticket_bug_system.dto.*;
import com.example.ticket_bug_system.entity.Module;
import com.example.ticket_bug_system.entity.Ticket;
import com.example.ticket_bug_system.entity.UserDetail;
import com.example.ticket_bug_system.entity.UserModule;
import com.example.ticket_bug_system.enums.RoleType;
import com.example.ticket_bug_system.exception_handler.IdNotFoundException;
import com.example.ticket_bug_system.exception_handler.InvalidDataException;
import com.example.ticket_bug_system.repo.TicketRepo;
import com.example.ticket_bug_system.repo.UserModuleRepo;
import com.example.ticket_bug_system.request.TicketRequest;
import com.example.ticket_bug_system.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author chandrika.g
 * user
 * @ProjectName ticket_bug_system
 * @since 13-10-2023
 */
@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    TicketRepo ticketRepo;
    @Autowired
    UserModuleRepo userModuleRepo;
    @Override
    public GenericResponse saveTicket(TicketRequest dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetail user = (UserDetail) authentication.getPrincipal();
        if (user.getRole().equals(RoleType.USER)) {
            Optional<UserModule> userModule=userModuleRepo.findById(dto.getUserModuleDto());
            if(userModule.isPresent()){
                if(dto.getUserModuleDto().equals(userModule.get().getId())){
                    userAndAdminSave(dto);
                }
            }
        }else if(user.getRole().equals(RoleType.ADMIN)){
            userAndAdminSave(dto);
        }return null;
    }
    //Admin can view all modules and the corresponding assigned users to those modules & also the tickets raised for module

    @Override
    public GenericResponse getAllTicket() {
        List<Ticket> ticketList = ticketRepo.findAll();
        if (!ticketList.isEmpty()) {
            return new GenericResponse(HttpStatus.OK.value(), ticketList);
        }
        else throw new NullPointerException("tickets are not found");
    }



    public GenericResponse userAndAdminSave(TicketRequest dto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetail user = (UserDetail) authentication.getPrincipal();
        Ticket ticket=new Ticket();
        if (dto.getId() != null) {
            return ticketUpdate(dto);
        } else {
            ticket = new Ticket();
            if (dto.getTitle() == null || dto.getStatus() == null || dto.getDescription() == null || dto.getUserModuleDto() == null) {
                throw new InvalidDataException("all fields are mandatory");
            } else {
                ticket.setTitle(dto.getTitle());
                ticket.setStatus(dto.getStatus());
                ticket.setDescription(dto.getDescription());
                ticket.setUserModule(userModuleRepo.findById(dto.getUserModuleDto()).orElseThrow(() -> new IdNotFoundException("user and module is not found")));
                ticketRepo.save(ticket);
                return new GenericResponse(HttpStatus.OK.value(), "ticket save successfully", entityToDto(ticket));
            }
        }
    }


    private TicketDto entityToDto(Ticket ticket) {
        TicketDto ticketDto=new TicketDto();
        ticketDto.setStatus(ticket.getStatus());
        ticketDto.setDescription(ticket.getDescription());
        ticketDto.setTitle(ticket.getTitle());
        ticketDto.setUserModuleDto(userModuleEntityToDto(ticket.getUserModule()));
        return null;
    }

    private UserModuleDto userModuleEntityToDto(UserModule userModule) {
        UserModuleDto userModuleDto=new UserModuleDto();
        userModuleDto.setId(userModule.getId());
        userModuleDto.setModuleDto(moduleEntityToDto(userModule.getModule()));
        userModuleDto.setUserDto(userEntityToDto(userModule.getUserDetail()));
        return userModuleDto;
    }

    private UserDto userEntityToDto(UserDetail userDetail) {
        UserDto dto = new UserDto();
        dto.setName(userDetail.getName());
        dto.setEmail(userDetail.getEmail());
        dto.setMobileNo(userDetail.getMobileNo());
        dto.setRole(userDetail.getRole());
        return dto;
    }

    private ModuleDto moduleEntityToDto(Module module) {
        ModuleDto moduleDto=new ModuleDto();
        moduleDto.setId(module.getId());
        moduleDto.setName(module.getName());
        moduleDto.setPriority(module.getPriority());
        moduleDto.setStatus(module.getStatus());
        return moduleDto;
    }

    private GenericResponse ticketUpdate(TicketRequest dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetail user = (UserDetail) authentication.getPrincipal();
        Optional<Ticket> optional=ticketRepo.findById(dto.getId());
        if(optional.isEmpty()){
            throw new IdNotFoundException("ticket id is not found");
        }
        Ticket ticket;
        ticket=optional.get();
        if(dto.getTitle()!=null) {
            ticket.setTitle(dto.getTitle());
        }
        if(dto.getStatus()!=null) {
            ticket.setStatus(dto.getStatus());
        }
        if(dto.getDescription()!=null) {
            ticket.setDescription(dto.getDescription());
        }
        if(dto.getUserModuleDto()!=null) {
            ticket.setUserModule(userModuleRepo.findById(dto.getUserModuleDto()).orElseThrow(() -> new IdNotFoundException("user and module is not found")));
        }
        ticketRepo.save(ticket);
        return new GenericResponse(HttpStatus.OK.value(), "ticket save successfully", entityToDto(ticket));
    }
}
