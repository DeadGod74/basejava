package src.com.webapp.storage;

import src.com.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{

    @Override
    public void save(Resume resume) {
        // Проверяем, не превышен ли лимит хранилища. Если превышен, то выводим ошибку и выходим
        if (size >= storage_limit) {
            System.out.println("Storage is full. Cannot save more resumes.");
            return;
        }
        // Получаем индекс резюме по UUID
        int index = getIndex(resume.getUuid());
        // Если резюме уже существует, выводим ошибку и выходим
        if (index >= 0) {
            System.out.println("Resume with UUID " + resume.getUuid() + " already exists");
            return;
        }

        // Находим позицию для вставки
        index = -(index + 1); // Получаем позицию для вставки
        // Сохраняем резюме в хранилище
        doSave(resume, index);

        // Сдвинуть элементы вправо
        System.arraycopy(storage, index, storage, index + 1, size - index);
        // Вставляем резюме на найденную позицию
        storage[index] = resume;
        // Увеличиваем размер хранилища
        size++;
    }


    @Override
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

    @Override
    public void delete(String uuid) {
        // Получаем индекс резюме по UUID
        int index = getIndex(uuid);
        // Если резюме найдено, удаляем его
        if (index >= 0) {
            doDelete(index);
        } else {
            // Если резюме не найдено, выводим сообщение
            System.out.println("Resume with UUID " + uuid + " not found for deletion.");
        }
    }

    @Override
    public int getIndex(String uuid) {
        // Используем бинарный поиск для получения индекса резюме по UUID
        return Arrays.binarySearch(storage, 0, size, new Resume(uuid));
    }
}
