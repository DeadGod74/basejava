package src.com.webapp.storage;

import src.com.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage{
    protected static final int STORAGE_LIMIT = 10000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume" + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    public void save(Resume resume) {
        // Проверяем, существует ли уже резюме с таким UUID
        if (getIndex(resume.getUuid()) >= 0){
            System.out.println("Resume with UUID " + resume.getUuid() + "already exist");
            return;
        }
        // Проверяем, не превышен ли лимит хранилища
        if (size >= STORAGE_LIMIT) {
            System.out.println("Storage is full. Cannot save more resumes.");
            return;
        }
        // Сохраняем резюме в хранилище на текущей позиции размера
        doSave(resume, getIndex(resume.getUuid()));
        size++;
    }

    public void delete(String uuid) {
        // Получаем индекс резюме по UUID
        int index = getIndex(uuid);
        // Если резюме найдено, удаляем его
        if (index >= 0) {
            doDelete(index);
            storage[size-1] = null;
            size--;
        } else {
            // Если резюме не найдено, выводим сообщение
            System.out.println("src.com.webapp.model.Resume " + uuid + " not found for deletion.");
        }
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

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public abstract int getIndex(String uuid);

    protected abstract void doSave(Resume resume, int index);

    protected abstract void doUpdate(Resume resume, int index);

    protected abstract void doDelete(int index);

}
