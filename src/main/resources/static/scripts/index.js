async function clickedButton(el,weaponType){
    if(el.getElementsByTagName("img")[0].classList.contains("clicked-aero")){
        window.location = "characters?weaponType="+weaponType;
    }
}