For now, architecture can contain only the conceptual system:

```
[Outreach User]
|
| authorize / request report
v
[Outreach Reporting System]
|
+---- reads permitted Gmail information
|
+---- calculates aggregate metrics
|
+---- writes report data
|
v
[Google Sheets]
```
Then maintain an Open Questions section:

Interaction surface:
UNKNOWN

Authentication approach:
UNKNOWN — investigate Google OAuth

Gmail access mechanism:
UNKNOWN — technical spike required

Runtime/hosting:
UNKNOWN

Persistent database:
UNKNOWN — may not be necessary

Reporting destination:
Google Sheets currently assumed