package sample.service;

import sample.model.Medicine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MedicineService {

    private final String filePath =
            "data/medicines.txt";

    // =========================================================
    // LOAD MEDICINES
    // =========================================================

    public List<Medicine> loadMedicines() {

        List<Medicine> medicines =
                new ArrayList<>();

        try (BufferedReader reader =
                     new BufferedReader(
                             new FileReader(filePath)
                     )) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts =
                        line.split(",");

                String name =
                        parts[0];

                double price =
                        Double.parseDouble(parts[1]);

                int quantity =
                        Integer.parseInt(parts[2]);

                Medicine medicine =
                        new Medicine(
                                name,
                                price,
                                quantity
                        );

                medicines.add(medicine);
            }

        } catch (IOException e) {

            e.printStackTrace();
        }

        return medicines;
    }

    // =========================================================
    // ADD MEDICINE
    // =========================================================

    public void addMedicine(
            Medicine medicine) {

        try (BufferedWriter writer =
                     new BufferedWriter(
                             new FileWriter(
                                     filePath,
                                     true
                             )
                     )) {

            writer.write(
                    medicine.getName()
                            + ","
                            + medicine.getPrice()
                            + ","
                            + medicine.getQuantity()
            );

            writer.newLine();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    // =========================================================
    // UPDATE MEDICINE
    // =========================================================

    public void updateMedicine(
            Medicine oldMedicine,
            Medicine updatedMedicine) {

        List<Medicine> medicines =
                loadMedicines();

        for (int i = 0;
             i < medicines.size();
             i++) {

            Medicine medicine =
                    medicines.get(i);

            if (medicine.getName()
                    .equals(oldMedicine.getName())) {

                medicines.set(
                        i,
                        updatedMedicine
                );

                break;
            }
        }

        saveMedicines(medicines);
    }

    // =========================================================
    // SAVE ALL MEDICINES
    // =========================================================

    private void saveMedicines(
            List<Medicine> medicines) {

        try (BufferedWriter writer =
                     new BufferedWriter(
                             new FileWriter(filePath)
                     )) {

            for (Medicine medicine :
                    medicines) {

                writer.write(
                        medicine.getName()
                                + ","
                                + medicine.getPrice()
                                + ","
                                + medicine.getQuantity()
                );

                writer.newLine();
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}