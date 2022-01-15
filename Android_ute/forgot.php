<?php 
    require_once('connect.php');
    include_once('sendmail.php');
    $mail = $_POST['Email'];
    // $mail = "thien.tj0702@gmail.com";
    $sql = "select * from tblsinhvien where Email='$mail'";
    $result = $connect->query($sql);
    $response = array();

    if($result->num_rows > 0){
        echo 'success';
        $row = mysqli_fetch_assoc($result);
        $email = new Mailer();
       $email->sendMail($mail,$row['matKhauTK']);
       
        
    }else{
        echo 'fail';
    }

?>