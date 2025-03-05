package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.Arrays;

public class MainTestArrayStorage {
    public static void main(String[] args) {
        ArrayStorage storage = new ArrayStorage();

        // Тестирование сохранения и получения
        Resume r1 = new Resume("uuid1");
        storage.save(r1);
        assert storage.get("uuid1") != null : "src.com.webapp.model.Resume with uuid1 should exist";

        // Тестирование получения несуществующего резюме
        assert storage.get("uuid2") == null : "src.com.webapp.model.Resume with uuid2 should not exist";

        // Тестирование удаления
        Resume r2 = new Resume("uuid2");
        storage.save(r2);
        storage.delete("uuid2");
        assert storage.get("uuid2") == null : "src.com.webapp.model.Resume with uuid2 should be deleted";

        // Тестирование размера
        assert storage.size() == 1 : "Size should be 1 after adding and deleting one resume";

        // Тестирование очистки
        storage.clear();
        assert storage.size() == 0 : "Size should be 0 after clearing";

        //тестирование обновления
        Resume updatedResume1 = new Resume("uuid1");
        storage.update(updatedResume1);

        // Проверка обновленного резюме
        Resume retrievedResume = storage.get("uuid1");
        if (retrievedResume != null) {
            System.out.println("Updated Resume: " + retrievedResume.getUuid());
        } else {
            System.out.println("Resume not found.");
        }

        // Попытка обновить несуществующее резюме
        Resume nonExistentResume = new Resume("uuid2");
        storage.update(nonExistentResume);





        System.out.println("All tests passed.");
    }

}
