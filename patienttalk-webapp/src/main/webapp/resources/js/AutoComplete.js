function AutoComplete() {

    //private variables
    var Values = new Array();
    var dropLength = 0;
    var dropSelected = 0;
    var dropVisible = false;
    var dropUpdating = false;
    var keyPressed = false;
    var ctrlPressed = false;

    //public variables
    this.OutputDivId = "acHolder";

    //LOAD EVENT
    $().ready(function () {
        AutoComplete.FillValues();
    });

    AutoComplete.prototype.HookEvents = function () {
        var d = window.frames['frame_textarea_1'].document.getElementById('textarea');
        $(d).keyup(function (event) {
            this.keyPressed = true;
            if (event.keyCode == 17) {
                //ctr code up
                ctrlPressed = false;
                return false;
            }
            else if (event.keyCode == 32) {
                if (ctrlPressed) {
                    //show the auto commplete
                    AutoComplete.OpenAutoComplete();
                    return false;
                }
            }
            if (event.keyCode == 38 || event.keyCode == 40) {
                //key code for up and down
            }
            else {
                AutoComplete.FilterWord();
            }
        });
        $(d).keydown(function (event) {
            //first check for ctrl spacebar
            if (event.keyCode == 17) {
                ctrlPressed = true;
                return true;
            }
            if (dropVisible) {
                if (event.keyCode == 38) {
                    var list = $('#ACList');
                    list[0].selectedIndex = dropSelected;
                    if (dropSelected > 0) {
                        dropSelected--;
                    }
                    return false;
                }
                else if (event.keyCode == 40) {
                    var list = $('#ACList');
                    list[0].selectedIndex = dropSelected;
                    if (dropSelected < dropLength) {
                        dropSelected++;
                    }
                    return false;
                }
                else if (event.keyCode == 13) {
                    if (dropVisible) {
                        //ENTER KEY use selected item in list
                        SetWord();
                        return false;
                    }
                    else {
                        return true;
                    }
                }
                else {
                    return true;
                }

            }
            else {
                return true;
            }
        });
    }

    AutoComplete.prototype.HideAutoComplete = function () {
        document.getElementById('acHolder').style.display = "none";
        dropVisible = false;
    }

    AutoComplete.prototype.FilterWord = function () {
        //main filtering point
        var _find = FindSpace();
        var text = _find.text;

        if (text != "" && text != " " && text != "\n") {
            //search list
            var filteredList = new Array();
            for (var i = 0; i < Values.length; i++) {
                if (Values[i].substring(0, text.length - 1) == text.substring(1) && Values[i] != text.substring(1)) {
                    filteredList[filteredList.length] = Values[i];
                }
            }
            if (filteredList.length > 0) {
                //adjust height of pop
                if (filteredList.length < 10) {
                    document.getElementById('acHolder').style.height = "auto";
                }
                else {
                    document.getElementById('acHolder').style.height = "180px";
                }
                var listSize = filteredList.length;
                //if its only 1 , say 2 because i dont want the drop icon in the list, workaround need proper fix to make it nicer
                if (filteredList.length == 1) {
                    listSize = 2;
                }
                var html = "<SELECT onclick=\"javascript:AutoComplete.ChooseItem();\" class=\"ACList\" id=\"ACList\" NAME=\"ACList\" SIZE=\"" + listSize + "\">";
                for (var i = 0; i < filteredList.length; i++) {
                    html += "<OPTION style=\"background-image:url('../images/autocomplete/property.png');\">" + filteredList[i] + "</OPTION>";
                }
                html += "</SELECT>";
                dropLength = filteredList.length - 1;
                ShowAutoComplete(html);
            }
            else {
                AutoComplete.HideAutoComplete();
            }
        }
        else {
            AutoComplete.HideAutoComplete();
        }
    }

    AutoComplete.prototype.ChooseItem = function () {
        //mouse click selection event
        SetWord();
    }

    AutoComplete.prototype.OpenAutoComplete = function () {
        //displays the list from ctl+spacebar
        //adjust height of pop
        if (Values.length < 10) {
            document.getElementById('acHolder').style.height = "auto";
        }
        else {
            document.getElementById('acHolder').style.height = "180px";
        }
        var listSize = Values.length;
        //if its only 1 , say 2 because i dont want the drop icon in the list, workaround need proper fix to make it nicer
        if (Values.length == 1) {
            listSize = 2;
        }
        var html = "<SELECT onclick=\"javascript:AutoComplete.ChooseItem();\" class=\"ACList\" id=\"ACList\" NAME=\"ACList\" SIZE=\"" + listSize + "\">";
        for (var i = 0; i < Values.length; i++) {
            html += "<OPTION style=\"background-image:url('../images/autocomplete/property.png');\">" + Values[i] + "</OPTION>";
        }
        html += "</SELECT>";
        dropLength = Values.length - 1;
        ShowAutoComplete(html);
    }

    var SetWord = function () {
        //gets the selected item and applies to the editor
        var list = $('#ACList');
        var dropId = list[0].selectedIndex;
        var selValue = document.forms[0].ACList.options[dropId];
        var text = FindSpace();
        editAreaLoader.setSelectionRange("textarea_1", text.startPos + 1, text.finishPos);
        editAreaLoader.setSelectedText("textarea_1", "");
        editAreaLoader.insertTags("textarea_1", selValue.innerText + ":", ";");
        AutoComplete.HideAutoComplete();
    }

    var ShowAutoComplete = function (html) {
        //displays the auto complete dialugue
        //get carot position to show the pop at
        var x = 0;
        var y = 0;

        var frameDoc = GetEditorWindow();

        if (frameDoc.getSelection) {
            //chrome 
            var cursor = frameDoc.getElementById('cursor_pos');
            x = (cursor.cursor_top + 30);
            y = (cursor.cursor_left - 3);

        }
        else {
            //ie
            var d = frameDoc;
            var refT = d.getElementById('textarea');
            refT.caretPos = d.selection.createRange();
            var posT = findPos(refT.caretPos);
            posT[0] += d.documentElement.scrollLeft;
            posT[1] += d.documentElement.scrollTop;
            x = posT[1];
            y = posT[0];
        }
        document.getElementById('acHolder').innerHTML = html;
        document.getElementById('acHolder').style.top = (x + 50) + "px";
        document.getElementById('acHolder').style.left = (y + 5) + "px";
        document.getElementById('acHolder').style.display = "block";
        document.getElementById('acHolder').style.zIndex = 9999999;
        dropSelected = 0;
        dropVisible = true;
    }

    var GetEditorWindow = function () {
        //generic function to return the active iframe handle, single point if needed to be updated for cross browser
        return window.frames['frame_textarea_1'].document;
    }

    var FindSpace = function () {
        //func to find the word from start carot position backwords to " "

        if (!dropUpdating) {
            var sel = editAreaLoader.getSelectionRange("textarea_1");
            var start = sel["start"];
            var original = window.frames['frame_textarea_1'].document.getElementById('textarea').value;
            //go backwards in search of space
            var back = start - 1;
            var text = "";
            var startFound = 0;
            while (back != 0) {
                //use the actual textarea underneath to find the word as setSelectionRange() messes with user typing positions
                //editAreaLoader.setSelectionRange("textarea_1", back, start);
                //text = editAreaLoader.getSelectedText("textarea_1");
                text = original.substring(back, start);
                if (text.substring(0, 1) == " " || text.substring(0, 1) == "\n") {
                    //editAreaLoader.setSelectionRange("textarea_1", start, start);
                    startFound = back;
                    back = 0;
                }
                else {
                    back--;
                }
            }
            return new FoundText(text.toLowerCase(), startFound, start);
        }
        else {
            return new FoundText("", 0, 0);
        }
    }
    var FoundText = function (text, start, finish) {
        this.text = text;
        this.startPos = start;
        this.finishPos = finish;
    }

    var findPos = function (obj) {
        var curleft = curtop = 0;
        if (obj) {
            do {
                curleft += obj.offsetLeft;
                curtop += obj.offsetTop;
            } while (obj = obj.offsetParent);
        }
        return [curleft, curtop];
    }

    AutoComplete.prototype.FillValues = function () {
        Values = new Array();
        //grabs the property grids values and uses them in the auto complete
        var GridValues = PropertyGrid.RetrieveCSSName();
        for (var i = 0; i < GridValues.length; i++) {
            Values[Values.length] = GridValues[i][0];
        }
    }
}