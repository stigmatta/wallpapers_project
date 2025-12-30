package com.odintsov.wallpapers_project.application.usecases.RegisterUser;

import com.odintsov.wallpapers_project.application.exceptions.EmailAlreadyTakenException;
import com.odintsov.wallpapers_project.application.exceptions.PhoneNumberAlreadyTakenException;
import com.odintsov.wallpapers_project.application.exceptions.SomeFieldsAreEmpty;
import com.odintsov.wallpapers_project.application.exceptions.UsernameAlreadyTakenException;

/**
 * Use case responsible for registering a new user in the system.
 * <p>
 * This component performs initial data validation and ensures that the
 * identity constraints (unique username, email, and phone number) are satisfied
 * before persisting the new user entity.
 */
public interface RegisterUserUseCase {

    /**
     * Executes the registration process.
     *
     * @param command the data transfer object containing user registration details
     * @throws SomeFieldsAreEmpty
     * if required fields are missing
     * @throws UsernameAlreadyTakenException
     * if the username is already registered
     * @throws EmailAlreadyTakenException
     * if the email is already registered
     * @throws PhoneNumberAlreadyTakenException
     * if the phone number is already registered
     */
    void execute(RegisterUserCommand command);
}