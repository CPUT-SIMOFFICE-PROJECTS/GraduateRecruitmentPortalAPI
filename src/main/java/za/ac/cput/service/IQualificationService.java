package za.ac.cput.service;

import za.ac.cput.model.Qualification;

import java.util.List;

public interface IQualificationService extends IService <Qualification, String> {
    void deleteById(String id);
}
