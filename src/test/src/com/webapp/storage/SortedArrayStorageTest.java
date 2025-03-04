package src.com.webapp.storage;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import src.com.webapp.exception.NotExistStorageException;
import src.com.webapp.model.Resume;

import static org.junit.Assert.*;

public class SortedArrayStorageTest extends AbstractArrayStorageTest {

    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }

}