<?php

if ( isset($_POST['first']) && isset($_POST['last']) && isset($_POST['email']) )
{
	//validating each field length
	if (strlen($_POST['first'])>0 && strlen($_POST['last'])>0 && strlen($_POST['email'])>0)
	{
		//validate email
		if (strpos($_POST['email'],'@')>0)
		{
			$array = array($_POST['first'],$_POST['last'],$_POST['email']);
			if (write_array_to_file_line('ex1_students.txt',$array))
			{
				echo 'Data inserted successfuly.';
			}
			else
			{
				echo 'Error inserting data.';
			}
		}
		else
		{
			echo 'E-mail is not valid';
		}
	}
	else
	{
		echo "Some fields has no data.";
	}
}
else
{
	echo 'No data';
}

function write_array_to_file_line($filename,$array)
{
	$handle = fopen($filename,'a') or die("File: $filename cannot be opened");
	$line = implode(',',$array);
	$result = fwrite($handle,"\n$line");//ON WINDOWS, replace "\n" with "\r\n"
	fclose($handle);
	return ($result > 0);
}
?>