public class MainArray {
    public static void main(String[] args) {
        ArrayStorage storage = new ArrayStorage();

        // Создаем несколько резюме
        Resume r1 = new Resume("uuid1");
        Resume r2 = new Resume("uuid2");

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
