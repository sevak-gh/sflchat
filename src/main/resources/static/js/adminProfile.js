$(document).ready(function(){
    $(".delete").click(function(){
        if (!confirm("Are you sure you want to delete user?")){
          return false;
        }
    });    
});
