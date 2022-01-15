<?php
    
        require_once('connect.php');
        $maSV = $_POST['maSV'];
        $matKhauTK = $_POST['matKhauTK'];
        // $maSV = "1911504203156";
        // $matKhauTK = "cuong@123";
        $sql = "select * from tblsinhvien where maSV='$maSV' and matKhauTK='$matKhauTK'";
        $result = $connect->query($sql);
        if($result->num_rows > 0){
            echo "success";
        }else{
            echo "fail";
        }
    
?>