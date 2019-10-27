package com.eliseev.app.models;

public class Siswa {
    private int id;
    private String nama;
    private String jurusan;

    public Siswa(String nama, String jurusan) {
        this.nama = nama;
        this.jurusan = jurusan;
    }

    public Siswa(int id, String nama, String jurusan) {
        this.id = id;
        this.nama = nama;
        this.jurusan = jurusan;
    }

    public Siswa() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    @Override
    public String toString() {
        return "Siswa{" +
                "id=" + id +
                ", nama='" + nama + '\'' +
                ", jurusan='" + jurusan + '\'' +
                '}';
    }

}
