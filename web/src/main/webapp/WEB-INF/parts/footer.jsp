<footer class="footer">
    <div class="container">
        <div>
            <p>
                Made by <a href="https://robinvdb.me" target="_blank" rel="noopener">Robin Van den Broeck</a> for
                IP-Major.
                View the source code at <a
                    href="https://github.com/ucll-ip-2018/project-internet-programming-2018-RobinVdBroeck"
                    target="_blank" rel="noopener">Github</a>
                - <a id="language-english" href="">English</a> | <a id="language-dutch" href="">Nederlands</a>
            </p>
        </div>
    </div>
</footer>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"
></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"
></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"
></script>
<script>
    /**
     * Change the local
     * @param local Which local to use
     */
    function switchLocal(local) {
        document.cookie = 'runetracker_local=' + local;
        reload()
    }

    /**
     * Reload the window
     */
    function reload() {
        window.location = window.location;
    }

    document.querySelector("#language-english").addEventListener("click", function() {
        switchLocal("en");
    });

    document.querySelector("#language-dutch").addEventListener("click", function () {
        switchLocal("nl")
    });
</script>
</body>
</html>
