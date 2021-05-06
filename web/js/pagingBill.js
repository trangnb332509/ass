function rederPager(id,pageindex,total,gap,eid)
{
    var container = document.getElementById(id);
    if(pageindex > gap+1){
        container.innerHTML +="<a href='listBill?pageindex=1&cid="+eid+"'>First</a>";
    }
    
    for(var i = pageindex-gap; i < pageindex; i++){
        if(i>=1){
            container.innerHTML +="<a href='listBill?pageindex="+i+"&cid="+eid+"'>"+i+"</a>";
        }
    }
    
    container.innerHTML +="<span>"+pageindex+"</span>";
    
    for(var i = pageindex+1; i <= pageindex+gap; i++){
        if(i<=total){
            container.innerHTML +="<a href='listBill?pageindex="+i+"&cid="+eid+"'>"+i+"</a>";          
        }
    }
    
    if(pageindex < total-gap){
        container.innerHTML +="<a href='listBill?pageindex="+total+"&cid="+eid+"'>Last</a>";
    }
}


