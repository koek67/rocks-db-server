package rocksdb;

import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

import java.util.Optional;

/**
 * Created by koushikkrishnan on 2/8/17.
 */
public class SimpleRocksDB {

    private RocksDB db;

    public SimpleRocksDB(RocksDB db) {
        this.db = db;
    }

    public Optional<String> get(String key) {
        Optional<String> value = Optional.empty();
        try {
             value = Optional.ofNullable(db.get(key.getBytes())).map(x -> new String(x));
        } catch (RocksDBException e) {
            e.printStackTrace();
        }
        return value;
    }

    public boolean set(String key, String value) {
        try {
            db.put(key.getBytes(), value.getBytes());
            return true;
        } catch (RocksDBException e) {
            e.printStackTrace();
            return false;
        }
    }
}