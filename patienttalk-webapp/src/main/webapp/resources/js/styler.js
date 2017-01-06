function Styler() {

    //private variables
    var screenWidth = document.documentElement.clientWidth;
    var screenHeight = document.documentElement.clientHeight;
    var tabIndex = 1;
    var tabSelected = 1;
    var Styles = new Array();
    var selectedStyleStart;
    var selectedStyleEnd;
    var selectedStyleId;
    var renderedLinks = false;

    //public variables
    this.OutputScreen = "outputScreen";
    this.MultipleLoad = 0;
    this.MultipleFiles = new Array();
    this.TabSelected = tabSelected;
    this.UseAutoComplete = true;
    this.CSSFormatter = new CSSFormatter();

    //public methods
    Styler.prototype.ScreenWidth = function () { return screenWidth; }

    //private methods
    var PrivateMethod = function (example) { }

    //LOAD EVENT
    $().ready(function () {
        $("#MySplitter").css('height', screenHeight - 35);
        $("#TopPane").css('height', screenHeight / 2);
        $("#LeftPane").css('width', (screenWidth - 300));
        $("#pg1").css('height', (screenHeight - 55));
        $("#textarea_1").css("height", (screenHeight / 2));

        //attach menu btns
        $('#btnNew').click(function () {
            Styler.NewDocument(".style{\n\n}", "Style " + tabIndex);
        });
        $('#btnOpen').click(function () {
            OpenFile();
        });
        $('#btnSave').click(function () { SaveCss(); });
        $('#btnUndo').click(function () { Styler.Undo(); });
        $('#btnRedo').click(function () { Styler.Redo(); });
        $('#btnSearch').click(function () { Styler.Search(); });
        $('#btnSyntaxHighlight').click(function () {
            Styler.SyntaxHighlight();
        });
        //$('#btnSmoothSelection').click(function () { Styler.SmoothSelection(); });
        $('#btnWordWrap').click(function () { Styler.WordWrap(); });
        $('#btnWebGetCSS').click(function () { ExtractFromSite(); });
        $('#btnHelp').click(function () { alert('Web CssEdit 2011 was created Jan 2011\n\nBy colinharman@hotmail.com, any suggestions/comments email me'); });
        $('#btnTidy').click(function () { Styler.ShowCSSTidy(); });
        // Horizontal splitter, nested in the right pane of the vertical splitter.
        $("#LeftPane").splitter({
            splitHorizontal: true,
            sizeTop: true,
            accessKey: "H"
        });

        //create editor
        editAreaLoader.init({
            id: "textarea_" + tabIndex
            , EA_load_callback: "Styler.EditorLoaded"
            , EA_file_switch_on_callback: "Styler.TabChange"
            //, change_callback: "Styler.Ed"
            , syntax: "css"
            , start_highlight: true
            , allow_toggle: false
        });

        //resize bottom output pane
        $("#frameHolder").css('height', (screenHeight / 2) - 42);

    });

    Styler.prototype.SwitchStyle = function (style) {
        switch (style) {
            case "css2":
                PropertyGrid.FillCss2();
                break;
            case "css3":
                PropertyGrid.FillCss3();
                break;
            default:
                break;
        }
        PropertyGrid.HideItems();
        if (this.UseAutoComplete) {
            AutoComplete.FillValues();
        }
    }

    Styler.prototype.NewDocument = function (css, fileName) {
        //create a new document
        try {
            //hide any elements open
            PropertyGrid.HideItems();
            PropertyGrid.ClearValues();

            //load new file into editor
            var new_file = { id: fileName, text: css, syntax: 'css', title: fileName };
            editAreaLoader.openFile('textarea_1', new_file);

            //rendering of the editor is complete so up the tabIndexer
            try {
                tabIndex++;
            } catch (ex) { alert(ex); }

            //check for multiple load
            if (Styler.MultipleLoad > 0) {
                Styler.MultipleLoad--;
                Styler.LoadDocs();
            }

        } catch (ex) { alert(ex); }
    }

    Styler.prototype.LoadDocs = function () {
        //render out the output back from the website - might need to put a delay in
        Styler.NewDocument(Styler.MultipleFiles[Styler.MultipleLoad].Css, Styler.MultipleFiles[Styler.MultipleLoad].FileName);
    }

    Styler.prototype.Ed = function (css) {
        //alert('ff');
    }

    Styler.prototype.EditorLoaded = function (css) {
        StyleEditor();
        //create first tab with data in
        Styler.NewDocument("/*Comments are ignored by live preview*/ \nbody{\n    font-size:11px;\n    font-family:verdana;\n    margin: 25px;\n    padding: 0px;\n    color:brown;\n}\n.previewWindow{\n    width:auto;\n    border-bottom:1px solid #bfbfbf;\n    padding:5px;\n    font-weight:bold;\n    background-color:#e0ded9;\n    color:#333333;\n}\n.previewWindow .day{\n    color:red;\n    padding-left:20px;\n}\n.previewWindow .day .blueText{\n    color:blue;\n}\na{\n    color:#3778fa;\n    font-style:italic;\n}\na:hover{\n    text-decoration:none;\n}\ntable{\n    color:green;\n    border:5px dashed #d3d3d3;\n    width:65%;\n}\ntable td{\n     border-right:10px solid red;\n     text-align:center;\n     color:#000000;\n}", "Style " + tabIndex);
        //set the carot position for first load
        editAreaLoader.setSelectionRange("textarea_1", 175, 175);
        Styler.TranslateProperties();

        //autocomplete?
        if (Styler.UseAutoComplete) {
            AutoComplete.HookEvents();
        }
    }

    Styler.prototype.Redo = function () {
        //redo changes on editor active
        editAreaLoader.execCommand('textarea_1', 'redo', true);
    }
    Styler.prototype.Undo = function () {
        //undo changes on editor active
        editAreaLoader.execCommand('textarea_1', 'undo', true);
    }
    Styler.prototype.GoTo = function () {
        //undo changes on editor active
        editAreaLoader.execCommand('textarea_1', 'go_to_line', true);
    }
    Styler.prototype.ChangeFontSize = function (size) {
        //undo changes on editor active
        editAreaLoader.execCommand('textarea_1', 'change_font_size', size);
    }
    Styler.prototype.WordWrap = function () {
        //undo changes on editor active
        editAreaLoader.execCommand('textarea_1', 'toggle_word_wrap', editAreaLoader.execCommand('textarea_1', 'toggle_word_wrap'));
    }

    Styler.prototype.Search = function () {
        //redo changes on editor active
        editAreaLoader.execCommand('textarea_1', 'show_search', true);
    }
    Styler.prototype.SyntaxHighlight = function () {
        //undo changes on editor active
        editAreaLoader.execCommand('textarea_1', 'change_highlight', editAreaLoader.execCommand('textarea_1', 'change_highlight'));
    }

    Styler.prototype.SmoothSelection = function () {
        //undo changes on editor active
        editAreaLoader.execCommand('textarea_1', 'change_smooth_selection_mode', !editAreaLoader.execCommand('textarea_1', 'change_smooth_selection_mode'));
    }

    Styler.prototype.GetSelectedRange = function (id) {
        //gets the selected start and end carot position
        return editAreaLoader.getSelectionRange(id);
    }

    Styler.prototype.GetSelectedTab = function () {
        //gets the current selected tab
        var $tabs = $('#tabs').tabs();
        return $tabs.tabs('option', 'selected');
    }

    Styler.prototype.TabChange = function () {
        //tab change
        Styler.RenderStyles();
    }

    Styler.prototype.TranslateProperties = function (sel) {
        var sel = Styler.GetSelectedRange('textarea_1');
        ExtractCssValue(GetAllEditorText(), sel["start"]);
    }
    Styler.prototype.ShowDownloadPanel = function () {
        $("#pg1").css('height', (screenHeight - 130)); //55 original
        document.getElementById('downloadPanel').style.display = "block";
    }
    Styler.prototype.HideDownloadPanel = function () {
        $("#pg1").css('height', (screenHeight - 55)); //55 original
        document.getElementById('downloadPanel').style.display = "none";
    }
    Styler.prototype.GetActiveFile = function () {
        //returns data for active tab, name and data
        return editAreaLoader.getCurrentFile("textarea_1");
    }
    Styler.prototype.GetEditorText = function () {
        return GetAllEditorText();
    }
    Styler.prototype.SetEditorText = function (code) {
        //sets the editors text to code
        editAreaLoader.setValue("textarea_1", code)
    }
    var GetAllEditorText = function () {
        //returns the editor text for active tab
        return editAreaLoader.getValue("textarea_1");
    }

    Styler.prototype.UpdateCSSEditor = function (cssId, value) {
        //updates the active editor if the cssId tag is found
        if (selectedStyleStart == 0 && selectedStyleEnd == 0) {
            //not in tag soo dont look for ids
            alert('selectedStyleStart, selectedStyleEnd');
        }
        else {
            var html = GetAllEditorText();
            var htmlClass = html.substring(selectedStyleStart + 1, selectedStyleEnd);
            //first check if it exists in sub selection
            var foundCssId = htmlClass.indexOf(cssId);
            if (foundCssId == -1) {
                //dosent exist so append at the end of class
                var newHtml = "";
                if (html.substring(selectedStyleEnd - 1, selectedStyleEnd) == "\n") {
                    newHtml = html.substring(0, selectedStyleEnd) + "    " + cssId + ":" + value + ";\n";
                    newHtml += html.substring(selectedStyleEnd);
                }
                else {
                    newHtml = html.substring(0, selectedStyleEnd) + "\n    " + cssId + ":" + value + ";\n";
                    newHtml += html.substring(selectedStyleEnd);
                }
                editAreaLoader.setValue("textarea_" + tabSelected, newHtml);
            }
            else {
                //it exist so find the location of start and end and replace in main doc
                var start = html.indexOf(cssId, (selectedStyleStart + 1));
                while (foundCssId != -1) {
                    if (html.substring(start - 1, start) == "-") {
                        //invlaid item, is part of bigger property, loop again
                        start = html.indexOf(cssId, ((selectedStyleStart + 1) + foundCssId) + 2);
                        foundCssId = start;
                    }
                    else {
                        //make sure its not a subset of whats actually being called ie background-color: and where looking for colour, could put indexOf(' border')
                        var end = html.indexOf(';', start + 1);
                        if (end != -1) {
                            //found now replace
                            editAreaLoader.setSelectionRange("textarea_" + tabSelected, start, end)
                            editAreaLoader.setSelectedText("textarea_" + tabSelected, cssId + ":" + value);
                            foundCssId = -1;
                        }
                    }
                }
            }
            Styler.RenderStyles();
            //UPDATE THE END POINT AS ADDED NEW TEXT IN
            FindEndOfSelectedClass();
            editAreaLoader.setSelectionRange("textarea_" + tabSelected, selectedStyleEnd - 1, selectedStyleEnd)
        }
    }

    var GetEditorWindow = function () {
        //generic function to return the active iframe handle, single point if needed to be updated for cross browser
        return window.frames['frame_textarea_1'].document;
    }

    var StyleEditor = function () {
        var frameDoc = GetEditorWindow();
        var editorPageHeight = frameDoc.documentElement.clientHeight - 0 + "px";
        frameDoc.getElementById('editor').style.height = editorPageHeight;
        frameDoc.getElementById('result').style.height = editorPageHeight;
        frameDoc.getElementById('editor').style.borderWidth = '0px';
        frameDoc.getElementById('result').style.borderWidth = '0px';
        frameDoc.getElementById('toolbar_1').style.display = 'none';
        frameDoc.getElementById('toolbar_2').style.display = 'none';
        frameDoc.getElementById('result').style.overflow = 'auto';
        frameDoc.getElementById('result').style.width = 'auto';
        frameDoc.getElementById('line_number').style.borderRight = '1px solid #e0cfc2';
        frameDoc.getElementById('line_number').style.backgroundColor = '#fffff0';

        //add style sheet
        var cssLink = document.createElement("link");
        cssLink.href = "css/main.css";
        cssLink.rel = "stylesheet";
        cssLink.type = "text/css";
        frameDoc.body.appendChild(cssLink);

        frameDoc.getElementById('textarea').onkeyup = function () { Styler.RenderStyles(); Styler.TranslateProperties(); };
        frameDoc.getElementById('textarea').onclick = function () { Styler.TranslateProperties(); };

    }

    var ExtractCssValue = function (htmlIn, startIndex) {
        var html = htmlIn;
        var cssStyle = "";
        var cssTag = "";
        var cssSpan = "";
        selectedStyleStart = 0;
        selectedStyleEnd = 0;
        selectedStyleId = "";

        var sindex = startIndex;
        var found = false;
        var bcount = sindex - 1;
        while (found != true) {
            var value = html.substring(bcount, bcount + 1);
            if (value == "{") {
                //found start of the style, now find end
                selectedStyleStart = bcount;
                var foundEnd = false;
                var bcountEnd = bcount + 1;
                while (foundEnd != true) {
                    var valueEnd = html.substring(bcountEnd, bcountEnd + 1);
                    if (valueEnd == "}") {
                        foundEnd = true;
                        selectedStyleEnd = bcountEnd;
                        cssSpan = html.substring(bcount + 1, bcountEnd);
                    }
                    else if (valueEnd == "}") {
                        foundEnd = true;
                    }
                    bcountEnd++;
                    if (bcountEnd > html.length) {
                        //reached end of doc
                        return;
                    }
                }
                found = true;
                //try and get the id of the style the .name{}

                var bcount = sindex - 1;
                var foundIdTag = false;
                var foundIdStart = bcount;
                var foundIdBCount = selectedStyleStart;
                while (foundIdTag != true) {
                    var value = html.substring(foundIdBCount, foundIdBCount + 1);
                    if (value == "." || value == "#" || value == "\n") {
                        //check it isnt the first chr ie .color\n{
                        if (foundIdBCount != selectedStyleStart - 1) {
                            //found a starting point
                            selectedStyleId = html.substring(foundIdBCount + 1, selectedStyleStart);
                            if (selectedStyleId.indexOf(":") != -1) {
                                selectedStyleId = selectedStyleId.substring(0, selectedStyleId.indexOf(':'));
                            }
                            if (selectedStyleId.indexOf(" ") != -1) {
                                selectedStyleId = selectedStyleId.substring(0, selectedStyleId.indexOf(' '));
                            }
                            window.frames[Styler.OutputScreen].window.ScrollToDiv(selectedStyleId);
                            foundIdTag = true;
                        }
                    }
                    foundIdBCount--;
                    if (foundIdBCount < 0) {
                        foundIdTag = true;
                    }
                }
            }
            bcount--;
            if (bcount < 0) {
                found = true;
            }
        }
        PropertyGrid.HideItems();
        PropertyGrid.ClearValues();
        //split inside data
        var cssItems = cssSpan.split(';');
        for (var i = 0; i < cssItems.length; i++) {
            var cssValues = cssItems[i].split(':');
            if (cssValues[0] == undefined || cssValues[1] == undefined) {
                //usally last item had a ; so it sees as another item, just ignore it
            }
            else {
                cssTag = cssValues[0];
                cssStyle = cssValues[1];
                PropertyGrid.UpdateStyle(cssTag, cssStyle);
            }
        }
    }

    var FindEndOfSelectedClass = function () {
        //returns the end carot position of selcted class, ie after an insert or update the position changes, start dosnt tho
        var html = GetAllEditorText();
        var foundEnd = false;
        var bcountEnd = selectedStyleStart;
        while (foundEnd != true) {
            var valueEnd = html.substring(bcountEnd, bcountEnd + 1);
            if (valueEnd == "}") {
                foundEnd = true;
                selectedStyleEnd = bcountEnd;
            }
            bcountEnd++;
            if (bcountEnd > html.length) {
                //reached end of doc
                return;
            }
        }
    }

    Styler.prototype.ShowCSSTidy = function () {
        $("#dialog-csstidy").dialog(
            { modal: true, width: 450, title: 'Tidy CSS', resizable: false,
                buttons: { 'Close': function () { $(this).dialog('close'); },
                    'Run Tidy': function () {
                        var formatType = 0;
                        if (document.getElementById('formatOption1').checked) {
                            formatType = 1;
                        }
                        var tabCount = 1;
                        if (document.getElementById('tabIndent').value != "") {
                            tabCount = document.getElementById('tabIndent').value;
                        }
                        Styler.CSSFormatter.TabCount = tabCount;
                        var bracketLine = 0;
                        if (document.getElementById('bracketOption2').checked) {
                            bracketLine = 1;
                        }
                        Styler.CSSFormatter.FirstBracket = bracketLine;
                        Styler.SetEditorText(Styler.CSSFormatter.Format(Styler.GetEditorText(), formatType));
                        editAreaLoader.setSelectionRange("textarea_1", 1, 1);
                        $(this).dialog('close');
                    }
                }
            });
    }

    Styler.prototype.RenderStyles = function () {
        //creates the live preview screen on the bottom panel
        try {
            var html = GetAllEditorText();
            var styles = new Array();
            var segments = html.split('{');
            renderedLinks = false;
            for (var i = 0; i < segments.length; i++) {
                //step backwards to get the class or id or h2 a  type
                //at this point will have something like 'color:red} .name'
                if (segments[i].indexOf('}') != -1) {
                    //split again
                    var seg1 = segments[i].split('}');
                    //check for multi styles using same style
                    if (seg1[1].indexOf(',') != -1) {
                        //found split these items out
                        var subSegments = seg1[1].split(',');
                        for (var s = 0; s < subSegments.length; s++) {
                            styles[styles.length] = subSegments[s];
                        }
                    }
                    else {
                        styles[styles.length] = seg1[1];
                    }
                }
                else {
                    //start of css?
                    styles[styles.length] = segments[i];
                }
            }
            var x = 0;
            var htmlOut = "";
            while (x != styles.length) {
                var cssStyle = styles[x];

                //remove any css comments
                if (cssStyle.indexOf('/*') != -1 && cssStyle.indexOf('*/') != -1) {
                    cssStyle = cssStyle.substring(0, cssStyle.indexOf('/*')) + cssStyle.substring(cssStyle.indexOf('*/') + 2);
                }
                cssStyle = cssStyle.replace(/^\s*|\s*$/g, '');
                //first check for multiple elements ie '.name .name ul li'
                if (cssStyle.indexOf(' ') != -1) {
                    //start backwards as that is the last element in tag line
                    var innerItems = cssStyle.split(' ');
                    var closeTags = new Array();
                    var innerHtmlOut = "";
                    var namesTogether = "";
                    for (var i = 0; i < innerItems.length; i++) {
                        if (i > 0) {
                            namesTogether += " > " + innerItems[i];
                        }
                        else {
                            namesTogether += innerItems[i];
                        }
                        var result = "";
                        if (i == innerItems.length - 1) {
                            //end item add the name
                            result = CreatePreviewTag(innerItems[i], innerItems[i], true, namesTogether);
                        }
                        else {
                            result = CreatePreviewTag(innerItems[i], innerItems[i], true, null);
                        }
                        innerHtmlOut += result.html;
                        closeTags[closeTags.length] = result.closeTag;
                    }
                    //add the closing brackets on end
                    for (var i = closeTags.length - 1; i >= 0; i--) {
                        innerHtmlOut += closeTags[i];
                    }
                    htmlOut += innerHtmlOut + "<br/>";
                }
                else {
                    //one element
                    htmlOut += CreatePreviewTag(cssStyle, styles[x], false, null).html;
                }
                x++;
            }
            htmlOut += "<style>" + html + "body{overflow:auto;}</style><div id=\"positionTag\">&nbsp;</div>";

            //var iframe = document.getElementById(this.OutputScreen);
            //iframe.contentWindow.document.body.innerHTML = htmlOut;
            //iframe.contentWindow.ScrollToDiv(cssStyle);
            window.frames[this.OutputScreen].document.body.innerHTML = htmlOut;

        } catch (ex) { alert(ex); }
    }

    var CreatePreviewTag = function (cssStyle, style, multiPart, multiName) {
        var htmlOut = "";
        var cTag = "";
        if (!multiPart) {
            if (cssStyle.indexOf('.') != -1) {
                //class
                cssStyle = style.replace('.', '');
                cssStyle = cssStyle.replace(/^\s*|\s*$/g, '');
                htmlOut = "<div id=\"" + cssStyle + "\" class=\"" + cssStyle + "\">" + style + "</div><br/>";
            }
            else if (cssStyle.indexOf('#') != -1) {
                //id
                cssStyle = style.replace('#', '');
                cssStyle = cssStyle.replace(/^\s*|\s*$/g, '');
                htmlOut = "<div id=\"" + cssStyle + "\">" + style + "</div><br/>";
            }
            else if (cssStyle.indexOf('h2') != -1) {
                htmlOut = "<h2>Header h2 style</h2><br/>";
            }
            else if (cssStyle.indexOf('h3') != -1) {
                htmlOut = "<h3>Header h3 style</h3><br/>";
            }
            else if (cssStyle == 'body') {
                htmlOut = "Body html tag<br/><br/>";
            }
            else if (cssStyle == 'p') {
                htmlOut = "<div id=\"p\"><p>Paragraph tag</p></div><br/><br/>";
            }
            else if (cssStyle == 'li' || cssStyle == 'ul') {
                htmlOut = "<ul><li>List tag 1</li><li>List tag 2</li></ul><br/><br/>";
            }
            else if (cssStyle == 'table' || cssStyle == 'td' || cssStyle == 'th') {
                htmlOut = "<div id=\"table\"><table><tr><th>Table header</th><th>Table header</th><th>Table header</th></tr><tr><td>Table data</td><td>Table data</td><td>Table data</td></tr></table></div><br/><br/>";
            }
            else if (cssStyle == 'a' || cssStyle == 'a:hover' || cssStyle == 'a:link' || cssStyle == 'a:visited') {
                if (!renderedLinks) {
                    renderedLinks = true;
                    htmlOut = "<div id=\"a\"><a href=\"#\">Test Link</a></div><br/><br/>";
                }
            }
            else {
                //todo mus so sub items like .box p {}
            }
        }
        else {
            //multipart = true means i dont add a closing tag, attach to cTag for return so sub items can be appened
            if (cssStyle.indexOf('.') != -1) {
                //class
                cssStyle = style.replace('.', '');
                cssStyle = cssStyle.replace(/^\s*|\s*$/g, '');
                if (multiName != null) {
                    htmlOut = "<div id=\"" + cssStyle + "\" class=\"" + cssStyle + "\">" + multiName;
                }
                else {
                    htmlOut = "<div id=\"" + cssStyle + "\" class=\"" + cssStyle + "\">";
                }
                cTag = "</div>";
            }
            else if (cssStyle.indexOf('#') != -1) {
                //id
                cssStyle = style.replace('#', '');
                cssStyle = cssStyle.replace(/^\s*|\s*$/g, '');
                if (multiName != null) {
                    htmlOut = "<div id=\"" + cssStyle + "\">" + multiName;
                }
                else {
                    htmlOut = "<div id=\"" + cssStyle + "\">";
                }
                cTag = "</div>";
            }
            else if (cssStyle.indexOf('h2') != -1) {
                if (multiName != null) {
                    htmlOut = "<h2>" + multiName;
                }
                else {
                    htmlOut = "<h2>";
                }
                cTag = "</h2>";
            }
            else if (cssStyle.indexOf('h3') != -1) {
                htmlOut = "<h3>Header h3 style";
                cTag = "</h2>";
            }
            else if (cssStyle == 'body') {
                htmlOut = "Body html tag<br/><br/>";
            }
            else if (cssStyle == 'p') {
                if (multiName != null) {
                    htmlOut = "<div id=\"p\"><p>" + multiName;
                }
                else {
                    htmlOut = "<div id=\"p\"><p>";
                }
                cTag = "</p></div>";
            }
            else if (cssStyle == 'ul') {
                htmlOut = "<ul><li>";
                cTag = "</li></ul>";
            }
            else if (cssStyle == 'li') {
                if (multiName != null) {
                    htmlOut = "<li>" + multiName;
                }
                else {
                    htmlOut = "<li>";
                }
                cTag = "</li>";
            }
            else if (cssStyle == 'table') {
                htmlOut = "<table>";
                cTag = "</table>";
            }
            else if (cssStyle == 'th') {
                if (multiName != null) {
                    htmlOut = "<th>" + multiName;
                }
                else {
                    htmlOut = "<th>";
                }
                cTag = "</th>";
            }
            else if (cssStyle == 'td') {
                if (multiName != null) {
                    htmlOut = "<td>" + multiName;
                }
                else {
                    htmlOut = "<td>";
                }
                cTag = "</td>";
            }
            else if (cssStyle == 'a' || cssStyle == 'a:hover' || cssStyle == 'a:link' || cssStyle == 'a:visited') {
                if (multiName != null) {
                    htmlOut = "<a href=\"#\">" + multiName;
                }
                else {
                    htmlOut = "<a href=\"#\">";
                }
                cTag = "</a>";
            }
            else {
                //todo mus so sub items like .box p {}
            }
        }
        return { html: htmlOut, closeTag: cTag };
    }
}

