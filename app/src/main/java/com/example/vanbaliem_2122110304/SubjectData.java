package com.example.vanbaliem_2122110304;

/** Model dùng cho SẢN PHẨM (tên, link chi tiết, ảnh) */
public class SubjectData {
    public String SubjectName; // tên sản phẩm
    public String Link;        // link chi tiết (mở trình duyệt)
    public String Image;       // url ảnh (Picasso/Glide load)

    // BẮT BUỘC: constructor rỗng để Gson/Retrofit map được
    public SubjectData() {}

    public SubjectData(String subjectName, String link, String image) {
        this.SubjectName = subjectName;
        this.Link = link;
        this.Image = image;
    }

    @Override public String toString() {
        return SubjectName;
    }
}
