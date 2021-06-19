package usk.uas.emanager;

public class Item {
    int _id;
    String _namabarang;
    String _harga;
    String _satuan;


    public Item() {
    }

    public Item(String _namabarang, String _harga, String _satuan) {
        this._namabarang = _namabarang;
        this._harga = _harga;
        this._satuan = _satuan;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_namabarang() {
        return _namabarang;
    }

    public void set_namabarang(String _namabarang) {
        this._namabarang = _namabarang;
    }

    public String get_harga() {
        return _harga;
    }

    public void set_harga(String _harga) {
        this._harga = _harga;
    }

    public String get_satuan() {
        return _satuan;
    }

    public void set_satuan(String _satuan) {
        this._satuan = _satuan;
    }
}