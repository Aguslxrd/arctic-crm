package com.arcticnode.crm.Controller;

import com.arcticnode.crm.Dto.AdminUserDTO;
import com.arcticnode.crm.Dto.AuthResponse;
import com.arcticnode.crm.Dto.RegisterRequest;
import com.arcticnode.crm.Dto.UserRoleToChange;
import com.arcticnode.crm.Entities.*;
import com.arcticnode.crm.LogUtils.LoggingUtils;
import com.arcticnode.crm.Services.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
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
    @Autowired
    private IUserService userService;
    @Autowired
    private IEnterpriseService enterpriseService;

    @PutMapping("/users/changerole")
    public ResponseEntity<AuthEntity> changeUserRole(@RequestBody UserRoleToChange userRoleToChange){

        iAdminManagementService.changeUserRole(userRoleToChange.getEmail(), userRoleToChange.getUserType());
        loggingUtils.logAction("Cambio de permisos", "usuario : " + userRoleToChange.getEmail() +
                " ahora tiene permiso de : " + userRoleToChange.getUserType());
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/users/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {

        if (request == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        loggingUtils.logAction("Nuevo usuario de administracion", "se creo usuario de administracion : " + request.getAdminname());
        authService.register(request);
        return ResponseEntity.ok().build();
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
                        .username(user.getAdminName())
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
    public ResponseEntity<Page<LoggingEntity>> getAllLogs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<LoggingEntity> logsPage = loggingUtils.getLogs(pageable);
        return ResponseEntity.ok(logsPage);
    }


    @GetMapping("/closedcases")
    public ResponseEntity<Page<CaseEntity>> getAllClosedCases(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CaseEntity> casesPage = caseService.findAllClosedCases(pageable);
        return ResponseEntity.ok(casesPage);
    }

    @GetMapping("/softdeletedusers")
    public ResponseEntity<Page<UserEntity>> getAllSoftDeletedUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserEntity> usersPage = userService.findAllSoftDeletedUsers(pageable);
        return ResponseEntity.ok(usersPage);
    }

    @GetMapping("/softdeletedenterprises")
    public ResponseEntity<Page<EnterpriseEntity>> getAllSoftDeletedEnterprises(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<EnterpriseEntity> enterprisePages = enterpriseService.findAllBySoftDeleteTrue(pageable);
        return ResponseEntity.ok(enterprisePages);
    }

    @PutMapping("/enterprises/activate/{enterpriseId}")
    public ResponseEntity<EnterpriseEntity> activateEnterprise(@PathVariable Integer enterpriseId) {
        Optional<EnterpriseEntity> activatedEnterprise = enterpriseService.activateEnterpriseById(enterpriseId);

        if (activatedEnterprise.isPresent()) {
            loggingUtils.logAction("Reactivación de empresa", "Se reactivó la empresa con ID: " + enterpriseId);
            return ResponseEntity.ok(activatedEnterprise.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
