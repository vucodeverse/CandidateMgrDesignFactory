package com.phongvu.gof;

import com.phongvu.gof.controller.CandidateController;
import com.phongvu.gof.creator.CandidateCreator;
import com.phongvu.gof.creator.ExperienceCreator;
import com.phongvu.gof.dto.CandidateDTO;
import com.phongvu.gof.factory.CandidateFactory;
import com.phongvu.gof.utils.validation.Validation;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // ===== 1. DI THỦ CÔNG =====
        List<CandidateCreator> creators = List.of(
                new ExperienceCreator()
                // new FresherCreator(),
                // new InternCreator()
        );

        CandidateFactory factory =
                new CandidateFactory(creators);

        CandidateController controller =
                new CandidateController(factory);

        // ===== 2. CHƯƠNG TRÌNH CHÍNH =====
        Scanner scanner = new Scanner(System.in);
        CandidateDTO dto = new CandidateDTO();
        int choice;

        do {
            System.out.print("The choice: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    do {
                        dto.setId(Validation.getString("Enter Candidate ID: "));
                        dto.setFirstName(Validation.getString("Enter First Name: "));
                        dto.setLastName(Validation.getString("Enter Last Name: "));
                        dto.setBirthDate(Validation.getInteger("Enter Birth Date: ", 1900, 2025));
                        dto.setAddress(Validation.getString("Enter Address: "));
                        dto.setPhone(Validation.getString("Enter Phone: "));
                        dto.setEmail(Validation.getString("Enter Email: "));
                        dto.setTypeCandidate(0);

                        dto.setYearExperience(
                                Validation.getInteger("Enter Years of Experience: ", 0, 100)
                        );
                        dto.setProfessionalSkill(
                                Validation.getString("Enter Professional Skill: ")
                        );

                        controller.addCandidate(controller.create(dto));

                    } while (Validation.getYN("Do you want to continue (Y/N)? "));
                    break;

                case 2:
                    // fresher – tương tự
                    break;

                case 3:
                    // intern – tương tự
                    break;

                case 4:
                    controller.printListNameCandidate();
                    break;
            }
        } while (true);
    }
}
