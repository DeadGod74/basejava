public class MainTestArrayStorage {
    public static void main(String[] args) {
        ArrayStorage storage = new ArrayStorage();

        // Тестирование сохранения и получения
        storage.save(new Resume("uuid1"));
        assert storage.get("uuid1") != null : "Resume with uuid1 should exist";

        // Тестирование получения несуществующего резюме
        assert storage.get("uuid2") == null : "Resume with uuid2 should not exist";

        // Тестирование удаления
        storage.save(new Resume("uuid2"));
        storage.delete("uuid2");
        assert storage.get("uuid2") == null : "Resume with uuid2 should be deleted";

        // Тестирование размера
        assert storage.size() == 1 : "Size should be 1 after adding and deleting one resume";

        // Тестирование очистки
        storage.clear();
        assert storage.size() == 0 : "Size should be 0 after clearing";

        System.out.println("All tests passed.");
    }

}
