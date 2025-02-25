package src.com.webapp;

import src.com.webapp.model.Resume;
import src.com.webapp.storage.ArrayStorage;
import src.com.webapp.storage.Storage;

public class MainArray {
    private final static Storage storage = new ArrayStorage();

    public static void main(String[] args) {

        // Создаем несколько резюме
        final Resume r1 = new Resume("uuid1");
        final Resume r2 = new Resume("uuid2");

        // Сохраняем резюме
        storage.save(r1);
        storage.save(r2);

        // Получаем резюме
        System.out.println(storage.get("uuid1"));

        // Удаляем резюме
        storage.delete("uuid1");

        // Проверяем размер
        System.out.println(storage.size());

        // Очищаем хранилище
        storage.clear();

        // Проверяем размер после очистки
        System.out.println(storage.size());
    }
}
