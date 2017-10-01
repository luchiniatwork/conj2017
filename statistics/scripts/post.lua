-- example HTTP POST script which demonstrates setting the
-- HTTP method, body, and adding a header

wrk.method = "POST"
wrk.body   = "{ products { name } categories { name }}"
wrk.headers["Content-Type"] = "application/graphql"

done = function(summary, latency, requests)
   io.write("------------------------------\n")
   for _, p in pairs({ 50, 90, 99, 99.999 }) do
      n = latency:percentile(p)
      io.write(string.format("%g%%,%d\n", p, n))
   end
   print(summary.errors.timeout)
end

--response = function(status, headers, body)
--   io.stderr:write(body .. "\n")
--end
