/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function rederPager(id,pageindex,total,gap,eid)
{
    var container = document.getElementById(id);
    if(pageindex > gap+1){
        container.innerHTML +="<a href='listButtonHole?pageindex=1&id="+eid+"'>First</a>";
    }
    
    for(var i = pageindex-gap; i < pageindex; i++){
        if(i>=1){
            container.innerHTML +="<a href='listButtonHole?pageindex="+i+"&id="+eid+"'>"+i+"</a>";
        }
    }
    
    container.innerHTML +="<span>"+pageindex+"</span>";
    
    for(var i = pageindex+1; i <= pageindex+gap; i++){
        if(i<=total){
            container.innerHTML +="<a href='listButtonHole?pageindex="+i+"&id="+eid+"'>"+i+"</a>";          
        }
    }
    
    if(pageindex < total-gap){
        container.innerHTML +="<a href='listButtonHole?pageindex="+total+"&id="+eid+"'>Last</a>";
    }
}


