import com.alex.webapp.model.Resume;
import com.alex.webapp.storage.SortedArrayStorage;
import com.alex.webapp.storage.Storage;

public class MainStorage {

    private final static Storage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        final Resume r1 = new Resume("uuid1");
        final Resume r2 = new Resume("uuid2");
        final Resume r3 = new Resume("uuid3");
        final Resume r4 = new Resume("uuid4");
        final Resume r5 = new Resume("uuid5");
        final Resume r6 = new Resume("uuid6");
        final Resume r7 = new Resume("uuid7");


        System.out.println(r1 == r2);

        ARRAY_STORAGE.save(r4);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r7);
        printAll();
        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r5);
        ARRAY_STORAGE.save(r3);
        ARRAY_STORAGE.save(r6);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        ARRAY_STORAGE.delete(r4.getUuid());
        printAll();
        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r4);
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());

    }
    static void printAll() {
        System.out.println("\nGet All");
        for (Resume resume : ARRAY_STORAGE.getAll()) {
            System.out.println(resume);
        }
    }
}
