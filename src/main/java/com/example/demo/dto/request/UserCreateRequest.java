package com.example.demo.dto.request;

import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.processing.Pattern;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString(exclude = "password")
@Schema(description = "Create user request")

public class UserCreateRequest {
   
    @NotBlank(message = "{validation.NotBlank}")
    @Size(max = 100, message = "{validation.Size}")
    @Schema(description = "Given name", example = "Kasun")
    private String firstName;

    @NotBlank(message = "{validation.NotBlank}")
    @Size(max = 100, message = "{validation.Size}")
    @Schema(description = "Family name", example = "Perera")
    private String lastName;

    @NotBlank(message = "{validation.NotBlank}")
    @Email(message = "{validation.Email}")
    @Size(max = 255, message = "{validation.Size}")
    @Schema(description = "Login email, stored lowercase", example = "kasun.perera@example.com")
    private String email;

    /** Plain text on the way in only. The service hashes it before it ever reaches the entity. */
    @NotBlank(message = "{validation.NotBlank}")
    @Size(min = 8, max = 72, message = "{validation.Size}")
    @Schema(description = "Raw password, hashed with BCrypt before storage", example = "S3cret!23")
    private String password;

    @Pattern(regexp = "^[+]?[1-9][0-9]{7,14}$", message = "{validation.Pattern}")
    @Schema(description = "E.164 phone number", example = "+94771234567")
    private String phoneNumber;

    @Size(max = 500, message = "{validation.Size}")
    @Schema(description = "Profile picture URL", example = "https://cdn.example.com/avatars/kasun.jpg")
    private String avatarUrl;

    @NotNull(message = "{validation.NotNull}")
    @Schema(description = "How the account authenticates", example = "LOCAL")
    private AuthProvider authProvider;

    /** Required when authProvider is not LOCAL, e.g. the Google "sub" claim. */
    @Size(max = 255, message = "{validation.Size}")
    @Schema(description = "External identity provider id")
    private String providerId;

    @NotNull(message = "{validation.NotNull}")
    @Schema(description = "Whether two factor auth is switched on", example = "false")
    private Boolean twoFactorEnabled;
    
}
