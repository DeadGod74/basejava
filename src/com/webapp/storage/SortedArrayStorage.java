package src.com.webapp.storage;

import src.com.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{

    @Override
    protected void doSave(Resume resume, int index) {
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = resume; // Вставляем резюме
    }


    @Override
    protected void doUpdate(Resume resume, int index) {
        storage[index] = resume; // Обновляем резюме по индексу
    }


    @Override
    protected void doDelete(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        storage[--size] = null; // Очищаем ссылку на последний элемент
    }

    @Override
    public int getIndex(String uuid) {
        // Используем бинарный поиск для получения индекса резюме по UUID
        return Arrays.binarySearch(storage, 0, size, new Resume(uuid));
    }
}
