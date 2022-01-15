<?php
    
        require_once('connect.php');
        $Email = $_POST['Email'];
        $matKhauEMAIL = $_POST['matKhauEMAIL'];
        // $Email = "1911504203156@sv.ute.udn.vn";
        // $matKhauEMAIL = "cuong@123";
        $sql = "select * from tblsinhvien where Email='$Email' and matKhauEMAIL='$matKhauEMAIL'";
        $result = $connect->query($sql);
        if($result->num_rows > 0){
            echo "success";
        }else{
            echo "fail";
        }
    
?>