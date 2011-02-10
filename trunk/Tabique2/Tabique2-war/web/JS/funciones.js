
    var dragapproved=false
    var z

    function drags(e){

        var targ
        if (!e) var e = window.event
        if (e.target) targ = e.target
        else if (e.srcElement) targ = e.srcElement
        if (targ.nodeType == 3) // defeat Safari bug
        targ = targ.parentNode



        if (targ.className=="userPapelera"){
            dragapproved=true
            z=targ
           // x=event.clientX
            //y=event.clientY
           // document.onmousemove=move
        }
    }

    function drops(e){
        var targ
        if (!e) var e = window.event
        if (e.target) targ = e.target
        else if (e.srcElement) targ = e.srcElement
        if (targ.nodeType == 3) // defeat Safari bug
        targ = targ.parentNode

        if (dragapproved){
            if (targ.className=="papelera"){
                formu=document.forms['formPapelera']
                atri=formu.elements["viejoUsuario"]
                atri.value = z.id
                formu.submit();
               //document.formPapelera.viejoUsuario = z.id
               //document.formPapelera.submit()


            }
            z=null
            dragapproved=false
        }
    }


    document.onmousedown=drags
    document.onmouseup=drops


