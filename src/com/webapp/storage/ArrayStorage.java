package src.com.webapp.storage;

import src.com.webapp.model.Resume;

import java.util.Objects;

public class ArrayStorage extends AbstractArrayStorage{

    public void save(Resume resume) {
        // Проверяем, существует ли уже резюме с таким UUID
        if (getIndex(resume.getUuid()) >= 0){
            System.out.println("Resume with UUID " + resume.getUuid() + "already exist");
            return;
        }
        // Проверяем, не превышен ли лимит хранилища
        if (size >= storage_limit) {
            System.out.println("Storage is full. Cannot save more resumes.");
            return;
        }
        // Сохраняем резюме в хранилище на текущей позиции размера
        doSave(resume, size);
    }

    public void update(Resume resume) {
        // Получаем индекс резюме по UUID
        int index = getIndex(resume.getUuid());
        // Если резюме найдено, обновляем его
        if (index >= 0) {
            doUpdate(resume, index);
        } else {
            // Если резюме не найдено, выводим сообщение
            System.out.println("Resume with UUID " + resume.getUuid() + " not found for update.");
        }
    }

    public void delete(String uuid) {
        // Получаем индекс резюме по UUID
        int index = getIndex(uuid);
        // Если резюме найдено, удаляем его
        if (index >= 0) {
            doDelete(index);
        } else {
            // Если резюме не найдено, выводим сообщение
            System.out.println("src.com.webapp.model.Resume " + uuid + " not found for deletion.");
        }
    }

    public int getIndex(String uuid) {
        // Перебор всех элементов в массиве storage
        for (int i=0; i < size; i++) {
            // Проверяем, совпадает ли переданный uuid с uuid текущего резюме в массиве
            if (Objects.equals(uuid, storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
