package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.ac.cput.exception.NotRegisteredUserException;
import za.ac.cput.exception.PasswordMismatchException;
import za.ac.cput.exception.UserExistsException;
import za.ac.cput.factory.GraduateFactory;
import za.ac.cput.model.Graduate;
import za.ac.cput.model.UserSession;
import za.ac.cput.repository.IGraduateRepository;
import za.ac.cput.serviceFacade.UserAuthenticatorServiceFacadeImpl;
import za.ac.cput.utility.Utility;


import java.util.List;
import java.util.Optional;

/**
 * @author Chuma Nxazonke
 * Student number: 219181187
 * Date: 25 November 2022
 */
@Service
public class GraduateServiceImpl implements IGraduateService, IUserAuthenticatorService<Graduate>{

    private final IGraduateRepository repository;
    private Graduate validatedGraduate;

    @Autowired
    public GraduateServiceImpl(IGraduateRepository repository){
        this.repository = repository;
    }

    public Graduate save(Graduate graduate) throws IllegalArgumentException
    {
        Optional<Graduate> persistedGraduate = findGraduateByEmail(graduate.getEmail());
        persistedGraduate.ifPresentOrElse(graduate1 -> validatedGraduate =
                GraduateFactory.build(graduate1.getUserId(),
                graduate.getFirstName(),
                graduate.getPreferredName(),
                graduate.getSurname(),
                graduate.getEmail(),
                graduate.getSecondaryEmail(),
                graduate1.getPassword(),
                graduate.getCellphone(),
                "GRADUATE",
                graduate.getMotorVehicleLicense(),
                graduate.getCountry(),
                graduate.getCv(),
                graduate.getQualifications(),
                graduate.getExperiences()),

                () -> validatedGraduate = GraduateFactory.build(
                        Utility.generateId(),
                        graduate.getFirstName(),
                        graduate.getPreferredName(),
                        graduate.getSurname(),
                        graduate.getEmail(),
                        graduate.getSecondaryEmail(),
                        UserAuthenticatorServiceFacadeImpl.hashPassword(graduate.getPassword()),
                        graduate.getCellphone(),
                        "GRADUATE",
                        graduate.getMotorVehicleLicense(),
                        graduate.getCountry(),
                        graduate.getCv(),
                        graduate.getQualifications(),
                        graduate.getExperiences()));

        return this.repository.save(validatedGraduate);
    }

    public Graduate signup(Graduate graduate) throws IllegalArgumentException, UserExistsException
    {
        Graduate validatedGraduate = GraduateFactory.build(
                Utility.generateId(),
                graduate.getEmail(),
                UserAuthenticatorServiceFacadeImpl.hashPassword(graduate.getPassword()),
                "GRADUATE");
        UserAuthenticatorServiceFacadeImpl.checkUserExists(findGraduateByEmail(graduate.getEmail()));
        return this.repository.save(validatedGraduate);
    }


    public Optional<Graduate> read(String graduateId)
    {
        return this.repository.findById(graduateId);
    }

    public Optional<Graduate> findGraduateByEmail(String email)
    {
        return this.repository.findByEmail(email);
    }

    public void deleteById(String graduateId)
    {
        this.repository.deleteById(graduateId);
    }

    public List<Graduate> findAll()
    {
        return this.repository.findAll();
    }

    public void delete(Graduate graduate)
    {
        this.repository.delete(graduate);
    }

    @Override
    public UserSession login(Graduate user) throws NotRegisteredUserException, PasswordMismatchException
    {
        Optional<Graduate> registeredUser = findGraduateByEmail(user.getEmail());
        return UserAuthenticatorServiceFacadeImpl.validateUserCredentials(
               registeredUser.isPresent(), user, registeredUser.get());
    }
}

