/* ============================================================
   URLForge — front-end wiring for the live API
   Base URL: https://urlforge-p2g3.onrender.com
   Endpoints used:
     POST /api/urls   { longUrl }  -> { shortUrl }
     GET  /{shortCode}             -> redirect (used natively via <a>/location)
   ============================================================ */

const API_BASE = "https://urlforge-p2g3.onrender.com";

/* Same pattern the backend validates against, mirrored here
   for instant client-side feedback before we ever hit the API. */
const URL_PATTERN = /^https?:\/\/([\w-]+\.)+[\w-]+(:\d+)?(\/.*)?$/;

document.addEventListener("DOMContentLoaded", () => {
    wireShortenForm();
    wireCopyButton();
    wireQrButton();
    wireValidateForm();
    wireAnalyticsButton();
});

/* ------------------------------------------------------------
   1. SHORTEN URL — calls POST /api/urls
------------------------------------------------------------ */
function wireShortenForm() {
    const form = document.getElementById("shortenForm");
    const input = document.getElementById("longUrlInput");
    const submitBtn = document.getElementById("shortenSubmitBtn");
    const iconBtn = document.getElementById("shortenIconBtn");
    const errorEl = document.getElementById("shortenError");
    const resultBox = document.getElementById("resultBox");
    const shortUrlText = document.getElementById("shortUrlText");
    const longUrlEcho = document.getElementById("longUrlEcho");

    if (!form) return;

    form.addEventListener("submit", async (e) => {
        e.preventDefault();
        hideError();

        const longUrl = input.value.trim();

        if (!longUrl) {
            showError("Paste a URL first.");
            return;
        }
        if (!URL_PATTERN.test(longUrl)) {
            showError("That doesn't look like a valid http:// or https:// URL.");
            return;
        }

        setLoading(true);

        try {
            const res = await fetch(`${API_BASE}/api/urls`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ longUrl }),
            });

            const data = await res.json().catch(() => ({}));

            if (!res.ok || !data.shortUrl) {
                showError(data.shortUrl || "Something went wrong. Please try again.");
                return;
            }

            renderResult(data.shortUrl, longUrl);
        } catch (err) {
            showError("Couldn't reach URLForge. The server may be waking up — try again in a few seconds.");
        } finally {
            setLoading(false);
        }
    });

    function setLoading(isLoading) {
        submitBtn.disabled = isLoading;
        iconBtn.disabled = isLoading;
        submitBtn.textContent = isLoading ? "Shortening…" : "Shorten URL";
        submitBtn.style.opacity = isLoading ? "0.7" : "1";
    }

    function showError(msg) {
        errorEl.textContent = msg;
        errorEl.style.display = "block";
    }

    function hideError() {
        errorEl.style.display = "none";
        errorEl.textContent = "";
    }

    function renderResult(shortUrl, longUrl) {
        shortUrlText.textContent = shortUrl.replace(/^https?:\/\//, "");
        shortUrlText.href = shortUrl;
        shortUrlText.dataset.fullUrl = shortUrl;
        longUrlEcho.textContent = `↳ from ${truncate(longUrl, 46)}`;
        resultBox.classList.add("reveal");
        resultBox.scrollIntoView({ behavior: "smooth", block: "nearest" });

        // reset QR preview since the link changed
        const qrWrap = document.getElementById("qrWrap");
        if (qrWrap) qrWrap.style.display = "none";
    }
}

/* ------------------------------------------------------------
   2. COPY LINK
------------------------------------------------------------ */
function wireCopyButton() {
    const copyBtn = document.getElementById("copyBtn");
    const shortUrlText = document.getElementById("shortUrlText");
    if (!copyBtn) return;

    copyBtn.addEventListener("click", async () => {
        const fullUrl = shortUrlText.href || shortUrlText.dataset.fullUrl || `https://${shortUrlText.textContent}`;
        try {
            await navigator.clipboard.writeText(fullUrl);
            flashLabel(copyBtn, "Copied!");
        } catch {
            flashLabel(copyBtn, "Couldn't copy");
        }
    });

    function flashLabel(btn, label) {
        const original = btn.textContent;
        btn.textContent = label;
        setTimeout(() => (btn.textContent = original), 1600);
    }
}

/* ------------------------------------------------------------
   3. GENERATE QR — uses a free public QR image API,
      encoding whatever short URL is currently displayed.
------------------------------------------------------------ */
function wireQrButton() {
    const qrBtn = document.getElementById("qrBtn");
    const qrWrap = document.getElementById("qrWrap");
    const qrImage = document.getElementById("qrImage");
    const shortUrlText = document.getElementById("shortUrlText");
    if (!qrBtn) return;

    qrBtn.addEventListener("click", () => {
        const fullUrl = shortUrlText.href || shortUrlText.dataset.fullUrl || `https://${shortUrlText.textContent}`;
        const encoded = encodeURIComponent(fullUrl);
        qrImage.src = `https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=${encoded}`;
        qrWrap.style.display = qrWrap.style.display === "none" ? "block" : "none";
    });
}

/* ------------------------------------------------------------
   4. VALIDATE URL — client-side format check.
      (No /api/validate endpoint exists yet — this mirrors the
      backend's own regex so feedback is instant and consistent.
      Swap in a real endpoint call here once one is available.)
------------------------------------------------------------ */
function wireValidateForm() {
    const form = document.getElementById("validateForm");
    const input = document.getElementById("validateInput");

    const outcomes = document.getElementById("validateOutcomes");
    const validEl = document.getElementById("outcomeValid");
    const invalidEl = document.getElementById("outcomeInvalid");

    if (!form) return;

    // Elements containing the text
    const validText = validEl.querySelector("span:last-child");
    const validSub = validEl.querySelector(".outcome-sub");

    const invalidText = invalidEl.querySelector("span:last-child");
    const invalidSub = invalidEl.querySelector(".outcome-sub");

    form.addEventListener("submit", async (e) => {
        e.preventDefault();

        const value = input.value.trim();

        if (!value) return;

        let shortCode;

        try {
            const url = new URL(value);
            shortCode = url.pathname.split("/").filter(Boolean).pop();
        } catch {
            shortCode = value;
        }

        try {
            const response = await fetch(`${API_BASE}/api/validate/${shortCode}`);

            if (!response.ok) {
                throw new Error("Request failed");
            }

            const exists = await response.json();

            outcomes.style.display = "grid";

            if (exists) {
                validEl.style.display = "flex";
                invalidEl.style.display = "none";

                validText.childNodes[0].textContent = "Link is Valid";
                validSub.textContent =
                    "This short URL exists in our database.";
            } else {
                validEl.style.display = "none";
                invalidEl.style.display = "flex";

                invalidText.childNodes[0].textContent = "Link Not Found";
                invalidSub.textContent =
                    "This short URL does not exist in our database.";
            }
        } catch (err) {
            console.error(err);

            outcomes.style.display = "grid";

            validEl.style.display = "none";
            invalidEl.style.display = "flex";

            invalidText.childNodes[0].textContent = "Server Error";
            invalidSub.textContent =
                "Unable to validate the URL. Please try again later.";
        }
    });
}

/* ------------------------------------------------------------
   5. ANALYTICS — no analytics endpoint in the current API spec,
      so this just acknowledges the lookup. Wire this up to a
      real GET /api/urls/{shortCode}/analytics once it exists.
------------------------------------------------------------ */
function wireAnalyticsButton() {
    const btn = document.getElementById("analyticsBtn");
    const input = document.getElementById("analyticsInput");
    const note = document.getElementById("analyticsNote");
    if (!btn) return;

    btn.addEventListener("click", () => {
        const value = input.value.trim();
        note.textContent = value
            ? `Showing sample data for "${value}" — live analytics endpoint isn't available yet.`
            : "Showing sample data — the analytics endpoint isn't live on the API yet.";
    });
}

/* ------------------------------------------------------------
   Utilities
------------------------------------------------------------ */
function truncate(str, max) {
    return str.length > max ? str.slice(0, max - 1) + "…" : str;
}