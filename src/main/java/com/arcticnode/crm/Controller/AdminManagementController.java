package com.arcticnode.crm.Controller;

import com.arcticnode.crm.Dto.AdminUserDTO;
import com.arcticnode.crm.Dto.AuthResponse;
import com.arcticnode.crm.Dto.RegisterRequest;
import com.arcticnode.crm.Dto.UserRoleToChange;
import com.arcticnode.crm.Entities.AuthEntity;
import com.arcticnode.crm.Entities.CaseEntity;
import com.arcticnode.crm.Entities.LoggingEntity;
import com.arcticnode.crm.LogUtils.LoggingUtils;
import com.arcticnode.crm.Services.IAdminManagementService;
import com.arcticnode.crm.Services.IAuthService;
import com.arcticnode.crm.Services.ICaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("http://localhost:4200")
public class AdminManagementController {

    @Autowired
    private IAdminManagementService iAdminManagementService;

    @Autowired
    private IAuthService authService;
    @Autowired
    private LoggingUtils loggingUtils;
    @Autowired
    private ICaseService caseService;

    @PutMapping("/users/changerole")
    public ResponseEntity<AuthEntity> changeUserRole(@RequestBody UserRoleToChange userRoleToChange){

        iAdminManagementService.changeUserRole(userRoleToChange.getEmail(), userRoleToChange.getUserType());
        loggingUtils.logAction("Cambio de permisos", "usuario : " + userRoleToChange.getEmail() +
                " ahora tiene permiso de : " + userRoleToChange.getUserType());
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/users/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {

        log.info("register request {}", request);
        loggingUtils.logAction("Nuevo usuario de administracion", "se creo usuario de administracion : " + request.getAdminname());
        return ResponseEntity.ok(authService.register(request));
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<AuthEntity> deleteUserById(@PathVariable Integer userId){
        if (userId == 0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        loggingUtils.logAction("Baja de usuario de administracion", "se elimino usuario de administracion con ID: " + userId);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/users")
    public ResponseEntity<List<AdminUserDTO>> getAllRegisteredUsers() {
        List<AuthEntity> allUsers = iAdminManagementService.getAllUsers();
        if (allUsers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<AdminUserDTO> userDTOs = allUsers.stream()
                .map(user -> AdminUserDTO.builder()
                        .userId(user.getId())
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .role(String.valueOf(user.getUserrole()))
                        .build())
                .collect(Collectors.toList());

        return ResponseEntity.ok(userDTOs);
    }

    @PatchMapping(value = "/users/edit")
    public ResponseEntity<AuthEntity> editUser(@RequestBody AuthEntity userToEdit) {
        try {
            AuthEntity updatedUser = iAdminManagementService.editUser(userToEdit);
            loggingUtils.logAction("Usuario de administracion editado", "se edito usuario de administracion : " + userToEdit.getUsername());
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/logs")
    public ResponseEntity<List<LoggingEntity>> getAllLogs() {
        List<LoggingEntity> logs = loggingUtils.getLogs();
        if (logs.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(logs);
    }


    @GetMapping("/closedcases")
    public ResponseEntity<List<CaseEntity>> getAllClosedCases() {
        List<CaseEntity> closedCases = caseService.findAllClosedCases();
        if (closedCases.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(closedCases);
    }

}
