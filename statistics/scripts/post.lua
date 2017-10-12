-- example HTTP POST script which demonstrates setting the
-- HTTP method, body, and adding a header

wrk.method = "POST"
wrk.body   = "{ products { name } categories { name }}"
wrk.headers["Content-Type"] = "application/graphql"
