$(document).ready(function(){
    $(".delete").click(function(){
        if (!confirm("Are you sure you want to remove user from chat room?")){
          return false;
        }
    });    
});
