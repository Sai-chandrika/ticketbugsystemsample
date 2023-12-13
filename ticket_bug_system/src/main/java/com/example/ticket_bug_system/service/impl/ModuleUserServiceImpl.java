package com.example.ticket_bug_system.service.impl;

import com.example.ticket_bug_system.dto.GenericResponse;
import com.example.ticket_bug_system.dto.ModuleDto;
import com.example.ticket_bug_system.dto.UserDto;
import com.example.ticket_bug_system.dto.UserModuleDto;
import com.example.ticket_bug_system.entity.Module;
import com.example.ticket_bug_system.entity.Ticket;
import com.example.ticket_bug_system.entity.UserDetail;
import com.example.ticket_bug_system.entity.UserModule;
import com.example.ticket_bug_system.enums.ModuleStatus;
import com.example.ticket_bug_system.enums.TicketStatus;
import com.example.ticket_bug_system.exception_handler.IdNotFoundException;
import com.example.ticket_bug_system.exception_handler.InvalidDataException;
import com.example.ticket_bug_system.exception_handler.mandidatoryFieldsException;
import com.example.ticket_bug_system.repo.ModuleRepo;
import com.example.ticket_bug_system.repo.TicketRepo;
import com.example.ticket_bug_system.repo.UserModuleRepo;
import com.example.ticket_bug_system.repo.UserRepo;
import com.example.ticket_bug_system.request.TicketRequest;
import com.example.ticket_bug_system.request.UserModuleRequest;
import com.example.ticket_bug_system.service.ModuleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author chandrika.g
 * user
 * @ProjectName ticket_bug_system
 * @since 13-10-2023
 */
@Service
public class ModuleUserServiceImpl implements ModuleUserService {
    @Autowired
    UserModuleRepo userModuleRepo;
    @Autowired
    ModuleRepo moduleRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    TicketRepo ticketRepo;

    public GenericResponse save(UserModuleRequest userModuleRequest) {
        UserModule userModule;
        if (userModuleRequest.getId() != null) {
            return updateAssignModuleToUser(userModuleRequest);
        } else {
            userModule=new UserModule();
            if (userModuleRequest.getUserDto() == null) {
                throw new mandidatoryFieldsException("user id is mandatory");
            }
            if (userModuleRequest.getModuleDto() == null) {
                throw new mandidatoryFieldsException("module id is mandatory");
            }
            userModule.setModule(moduleRepo.findById(userModuleRequest.getModuleDto()).orElseThrow(() -> new IdNotFoundException("module id is not found")));
            userModule.setUserDetail(userRepo.findById(userModuleRequest.getUserDto()).orElseThrow(() -> new IdNotFoundException("user id is not found")));
            userModuleRepo.save(userModule);
            return new GenericResponse(HttpStatus.OK.value(), "user successfully assign module to user " , entityToDto(userModule));
        }
    }

    private UserModuleDto entityToDto(UserModule userModule) {
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

    private GenericResponse updateAssignModuleToUser(UserModuleRequest userModuleRequest) {
        UserModule userModule;
        Optional<UserModule> optional = userModuleRepo.findById(userModuleRequest.getId());
        if (optional.isEmpty()) {
            throw new IdNotFoundException("assign module to user id not found");
        }
        userModule = optional.get();
        if(userModuleRequest.getModuleDto()!=null) {
            userModule.setModule(moduleRepo.findById(userModuleRequest.getUserDto()).orElseThrow(() -> new IdNotFoundException("module id is not found")));
        }userModule.setModule(optional.get().getModule());
        if(userModuleRequest.getUserDto()!=null){
            userModule.setUserDetail(userRepo.findById(userModuleRequest.getUserDto()).orElseThrow(() -> new IdNotFoundException("user id is not found")));
        }userModule.setUserDetail(optional.get().getUserDetail());
        userModuleRepo.save(userModule);
        return new GenericResponse(HttpStatus.OK.value(), "update successfully" , userModule);
    }
    @Override
    public GenericResponse ssaveModuleToUser(UserModuleRequest userModuleRequest){
        List<UserModule> m = userModuleRepo.findAllByUserDetailId(userModuleRequest.getUserDto()).stream().toList();
        if(!m.isEmpty()) {
            for (UserModule userModule1 : m) {
                if (userModule1.getModule().getId().equals(userModuleRequest.getModuleDto())) {
                  throw new InvalidDataException("this module already assigned to this person");
                }else return save(userModuleRequest);
            }
        }
         return save(userModuleRequest);
    }

//    @Scheduled(fixedRate = 60000)
//    public GenericResponse changeModuleStatus(String moduleId){
//    Optional<Module> moduleOptional=moduleRepo.findById(moduleId);
//    if(moduleOptional.isEmpty()) throw new IdNotFoundException("module is not found");
//        Module module=moduleOptional.get();
//        List<Ticket> ticketList=ticketRepo.findAllByUserModuleModuleId(moduleId);
//      int ticketSize=ticketList.size();
//      List<Ticket> completedTickets=new ArrayList<>();
//      List<Ticket> onHoldTickets=new ArrayList<>();
//      if(ticketSize>=1){
//        module.setStatus(ModuleStatus.IN_PROGRESS);
//      }
//      for(Ticket ticket:ticketList){
//          if(ticket.getStatus().equals(TicketStatus.COMPLETED)){
//              completedTickets.add(ticket);
//          }
//          if(ticket.getStatus().equals(TicketStatus.HOLD)){
//              onHoldTickets.add(ticket);
//          }
//      }
//      if(completedTickets.size()==ticketSize){
//          module.setStatus(ModuleStatus.COMPLETED);
//      }
//      if(onHoldTickets.size()>=5){
//          module.setStatus(ModuleStatus.HOLD);
//      }
//      return null;
//    }

    @Scheduled(fixedRate = 60000)
    public GenericResponse statusChange(){
        List<Module> modules=moduleRepo.findAll();
        List<Ticket> completedTickets=new ArrayList<>();
        List<Ticket> onHoldTickets=new ArrayList<>();
        for(Module module:modules){
            List<Ticket> ticketList=ticketRepo.findAllByUserModuleModuleId(module.getId());
            int ticketSize=ticketList.size();
            if(ticketSize>=1){
                module.setStatus(ModuleStatus.IN_PROGRESS);
            }
            for(Ticket ticket:ticketList){
                if(ticket.getStatus().equals(TicketStatus.COMPLETED)){
                    completedTickets.add(ticket);
                }
                if(ticket.getStatus().equals(TicketStatus.HOLD)){
                    onHoldTickets.add(ticket);
                }
            }
            if(completedTickets.size()==ticketSize){
                module.setStatus(ModuleStatus.COMPLETED);
            }
            if(onHoldTickets.size()>=5){
                module.setStatus(ModuleStatus.HOLD);
            }
            moduleRepo.save(module);
        }

        return null;
    }
}
