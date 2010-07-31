<?php

class crazyOverload
{
  private $real_name,$real_age;

  function __get($name)
  {
    if($name=="name")
    {
      //echo "name";
      return $this->real_age;
    }
    else if ($name=="age")
    {
      //echo "age";
      return $this->real_name;
      
    }
  }
  
  function __set($name,$value)
  {
    if ($name=="name")
    {
      $this->real_name=$value;
    }
    else if ($name=="age")
    {
      $this->
      real_age=$value;
    }
  }
}

$a=new crazyOverload();
$a->name="first name";
$a->age="4";
echo $a->name; //will print 4
echo "<br>";
echo $a->age; //will print first name

?>