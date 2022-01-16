<?php
    $connect = mysqli_connect("localhost", "root","","ute"); 
    mysqli_query($connect, "SET NAMES 'utf8'");

    // $masv = $_POST['MaSV'];
    $sql = "select s.maSV as masv, s.tenSinhVien as tenSV, m.tenMH as tenMH, c.TenCT as tenCTLHP, t.STC as stc, l.ten as tenLHP from tblsinhvien as s, tblmonhoc as m,  tblctlhp as c,  tblttlhp as t, tbllhp as l
    WHERE t.idsv = s.maSV and t.idctlhp = c.idCT and m.idMH = t.idmon and c.idLHP = l.idLHP";

    $data = mysqli_query($connect, $sql);
    class TTCT{
    function TTCT($masv,$tenCT,$tenMH, $STC, $tenLHP){
        $this->MaSV = $masv;
        $this->TenCTLHP = $tenCT;
        $this->TenMH = $tenMH;
        $this->STC = $STC;
        $this->TenLHP = $tenLHP;
    }
}
// 2. Tạo mảng
$mangEp = array();
while($row = mysqli_fetch_assoc($data)){
    array_push($mangEp, new TTCT($row['masv'],$row['tenCTLHP'],$row['tenMH'], $row['stc'],  $row['tenLHP']));
}
// 4. Chuyển định dạng của mảng -> JSON
echo json_encode($mangEp);

?>