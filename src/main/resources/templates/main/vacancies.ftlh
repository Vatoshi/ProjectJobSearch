<#import "../layout.ftlh" as main>
<#import "/spring.ftl" as spring>
<@main.layout >

<div class="container">
    <div class="sidebar">
        <h5><@spring.message "filters"/></h5>

        <form id="filter" action="/">
        <div class="filter-section">
            <label for="category"><@spring.message "sortBy"/></label>
            <select id="category" name="order">
                <option value="updateTime" <#if order?string == "updateTime"> selected </#if>><@spring.message "vacancies.ftlh.data"/></option>
                <option value="responses" <#if order?string == "responses"> selected </#if>><@spring.message "vacancies.flth.response"/></option>
            </select>
        </div>
        </form>
            <button class="action-btn" form="filter" type="submit"><@spring.message "vacancies.ftlh.takeFilter"/></button>


        <div class="pagination">
            <#if (currentPage >= 3)>
                <a href="?page=0" class="page-link" style="margin-right: 8px">1 ...</a>
            </#if>

            <#if (currentPage != 0)>
                <a href="?page=${currentPage - 1}" class="page-link">${currentPage}</a>
            </#if>

                <a href="?page=${currentPage}" class="page-link active">${currentPage + 1}</a>

            <#if (currentPage != vacancies.totalPages - 1)>
                <a href="?page=${currentPage + 1}" class="page-link">${currentPage + 2}</a>
            </#if>

            <#if currentPage < (vacancies.totalPages - 2)>
                <a href="?page=${vacancies.totalPages - 1}" class="page-link" style="margin-left: 8px">... ${vacancies.totalPages}</a>
            </#if>
        </div>

    </div>

    <div class="main-content">
        <h2 class="section-title"><@spring.message "vacancies"/></h2>
        <ul class="vacancy-list" id="vacancy-list">

            <#if vacancies? has_content>
            <#list vacancies.content as vacancy>
            <a href="/details?id=${vacancy.id}" style="text-decoration: none; transition: none; animation: none">
                <li class="vacancy-item">
                    <div class="vacancy-left">
                        <div class="vacancy-header">
                            <h3 class="vacancy-title">${vacancy.name!"Unknown"}</h3>
                        </div>
                        <div class="vacancy-date"><@spring.message "resumeList.lastChanges"/> ${vacancy.updateTime}</div>
                        <div class="vacancy-experience"><@spring.message "resumeList.workExp"/>: ${vacancy.expFrom} года</div>
                        <p class="vacancy-description">${vacancy.description!"-"}</p>
                        <#if user?? && user.id == 1><div class="vacancy-actions">
                            <button class="action-btn"><@spring.message "vacancies.ftlh.makeResponse"/></button>
                        </div></#if>
                    </div>
                    <div class="vacancy-company">${vacancy.name}</div>
                    <div class="vacancy-responses"><@spring.message "vacancies.ftlh.respondedUsers"/> ${vacancy.responses!"0"}</div>
                    <div class="vacancy-salary"><@spring.message "vacancies.ftlh.salary"/> ${vacancy.salary!"Договорная"} ₽ </div>
                </li>
                </a>
            </#list>
            </#if>
        </ul>
    </div>
</div>

<style>
    :root {
        --primary: #6a46eacc;
        --secondary: #8739fb;
        --light: #f8f9fa;
        --dark: #2d3748;
        --gray: #181d25;
        --light-gray: #e2e8f0;
    }

    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    }

    body {
        background-color: #f5f7fa;
        color: var(--dark);
        line-height: 1.6;
    }

    .container {
        max-width: 1200px;
        margin: 0 auto;
        padding: 20px;
        display: grid;
        grid-template-columns: 300px 1fr;
        gap: 30px;
    }

    .sidebar {
        background: white;
        border-radius: 10px;
        padding: 25px;
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
        height: fit-content;
    }

    .main-content {
        width: 100%;
        background: white;
        border-radius: 10px;
        padding: 30px;
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
    }

    .section-title {
        font-size: 1.3rem;
        font-weight: 600;
        margin-bottom: 20px;
        color: var(--dark);
        display: flex;
        align-items: center;
    }

    .section-title::after {
        content: '';
        flex: 1;
        height: 1px;
        background: var(--light-gray);
        margin-left: 15px;
    }

    .vacancy-list {
        list-style: none;
        padding-left: 0;
    }

    .vacancy-list a {
        text-decoration: none;
        color: inherit;
    }

    .vacancy-item {
        border: 1px solid var(--light-gray);
        border-radius: 8px;
        padding: 20px;
        margin-bottom: 15px;
        display: flex;
        justify-content: space-between;
        align-items: flex-start;
        position: relative;
        transition: all 0.3s;
    }

    .vacancy-item:hover {
        border-color: var(--primary);
        box-shadow: 0 2px 8px rgba(102, 126, 234, 0.1);
    }

    .vacancy-left {
        width: 70%;
    }

    .vacancy-header {
        display: flex;
        justify-content: space-between;
        margin-bottom: 10px;
    }

    .vacancy-title {
        font-size: 1.2rem;
        font-weight: 600;
    }

    .vacancy-company {
        font-size: 1rem;
        color: var(--gray);
        position: absolute;
        top: 20px;
        right: 20px;
    }

    .vacancy-date,
    .vacancy-experience {
        color: var(--gray);
        font-size: 0.9rem;
    }

    .vacancy-description {
        margin-top: 25px;
        font-size: 1rem;
        color: var(--dark);
        display: -webkit-box;
        -webkit-line-clamp: 3;
        -webkit-box-orient: vertical;
        overflow: hidden;
        text-overflow: ellipsis;
    }


    .vacancy-actions {
        display: flex;
        gap: 10px;
        margin-top: 20px;
    }

    .action-btn {
        padding: 5px 12px;
        font-size: 0.85rem;
        background: linear-gradient(to right, var(--primary), var(--secondary));
        color: white;
        border: none;
        border-radius: 6px;
        cursor: pointer;
        transition: all 0.3s ease;
    }

    .action-btn:hover {
        transform: translateY(-2px);
        box-shadow: 0 4px 8px rgba(102, 126, 234, 0.3);
    }

    .vacancy-responses {
        font-size: 0.8rem;
        font-weight: 600;
        color: gray;
        position: absolute;
        bottom: 50px;
        right: 20px;
    }

    .vacancy-salary {
        font-size: 1.1rem;
        font-weight: 600;
        color: var(--primary);
        position: absolute;
        bottom: 25px;
        right: 20px;
    }

    input[type="text"],
    select {
        width: 100%;
        padding: 8px;
        border-radius: 6px;
        border: 1px solid #ccc;
        margin-bottom: 20px;
    }

    .filter-section {
        margin-bottom: 20px;
    }

    @media (max-width: 768px) {
        .container {
            grid-template-columns: 1fr;
        }

        .sidebar {
            margin-bottom: 20px;
        }

        .vacancy-item {
            flex-direction: column;
            align-items: flex-start;
        }

        .vacancy-left {
            width: 100%;
            margin-bottom: 15px;
        }

        .vacancy-salary {
            position: static;
            margin-top: 10px;
        }

        .vacancy-company {
            position: static;
            margin-top: 10px;
        }
    }

    .top-bar {
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
        display: flex;
        justify-content: flex-end;
        padding: 20px;
        background-color: white;
        position: relative;
    }

    .profile {
        position: static;
        width: 150px;
        height: 50px;
        border: 1px solid #c6baf4cc;
        border-radius: 10px;
        display: flex;
        align-items: center;
        text-decoration: none;
        color: black;
        background: white;
        box-shadow: none;
        transition: box-shadow 0.3s ease;
    }


    .profile:hover {
        box-shadow: 0 0 5px #6a46eacc;
    }

    .div-img {
        width: 40px;
        height: 40px;
        overflow: hidden;
        border: 1px solid rgb(98, 98, 98);
        border-radius: 100%;
        margin-left: 9px;
        background-color: #f0f0f0;
        display: flex;
        align-items: center;
        justify-content: center;
    }

    .div-img img {
        width: 100%;
        height: auto;
        display: block;
        object-fit: cover;
    }

    .profile-text {
        font-size: 13px;
        font-weight: 600;
        margin-left: 10px;
        line-height: 1.2;
    }

    .pagination {
        display: flex;
        justify-content: center;
        gap: 10px;
        margin-top: 30px;
        flex-wrap: nowrap;
    }

    .page-link {
        margin-top: 20px;
        padding: 8px 13px;
        font-size: 0.65rem;
        font-weight: 500;
        color: var(--dark);
        text-decoration: none;
        background-color: white;
        border: 1px solid var(--light-gray);
        border-radius: 8px;
        box-shadow: 0 1px 5px rgba(0, 0, 0, 0.05);
        transition: all 0.3s ease;
    }

    .page-link:hover {
        color: white;
        background: linear-gradient(to right, var(--primary), var(--secondary));
        box-shadow: 0 2px 8px rgba(102, 126, 234, 0.2);
    }

    .page-link.active {
        background: linear-gradient(to right, var(--primary), var(--secondary));
        color: white;
        font-weight: 600;
        pointer-events: none;
    }

    h5 {
        margin-bottom: 20px;
    }
</style>
    <script>
        const form = document.getElementById('filter');

        function saveFilters(filters) {
            localStorage.setItem('postFilters', JSON.stringify(filters));
        }

        function restoreFilters() {
            const data = localStorage.getItem('postFilters');
            return data ? JSON.parse(data) : {};
        }

        window.addEventListener('DOMContentLoaded', () => {
            const savedFilters = restoreFilters();
            for (let [key, value] of Object.entries(savedFilters)) {
                const input = form.elements[key];
                if (input) {
                    input.value = value;
                }
            }
            const urlParams = new URLSearchParams(window.location.search);
            if (!urlParams.has('order') && savedFilters.order) {
                urlParams.set('order', savedFilters.order);
                window.location.search = urlParams.toString();
            }
        });

        form.addEventListener('submit', function (e) {
            e.preventDefault();
            const formData = new FormData(form);
            const filters = Object.fromEntries(formData);
            saveFilters(filters);
            const params = new URLSearchParams(filters).toString();
            window.location.search = params;
        });
    </script>

</@main.layout>

