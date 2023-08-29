package ru.neoflex.dossier.service;

public interface DossierService {
    void finishRegistrationByApplicationId(String emailMessage);

    void sendDocumentByApplicationId(String emailMessage);

    void signDocumentByApplicationId(String emailMessage);

    void codeDocumentByApplicationId(String emailMessage);
}
