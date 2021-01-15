/*
<script type="text/javascript">
    function handleLoginRequest(xhr, status, args) {
        if(args.validationFailed || !args.loggedIn) {
            PF('dlg').jq.effect("shake", {times:5}, 100);
        }
        else {
            PF('dlg').hide();
            $('#loginLink').fadeOut();
        }
    }
</script>*/

    function verificar(xhr, status, args, dlg, tbl) {
        if(args.validationFailed) {
            dlg.jq.effect("shake", {times:5}, 100);
        }
        else {
            dlg.hide();
            tbl.clearFilters();
        }
    }
