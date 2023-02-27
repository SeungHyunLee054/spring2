
// omokDoc.cpp: ComokDoc 클래스의 구현
//

#include "pch.h"
#include "framework.h"
// SHARED_HANDLERS는 미리 보기, 축소판 그림 및 검색 필터 처리기를 구현하는 ATL 프로젝트에서 정의할 수 있으며
// 해당 프로젝트와 문서 코드를 공유하도록 해 줍니다.
#ifndef SHARED_HANDLERS
#include "omok.h"
#endif

#include "omokDoc.h"
#include "omokView.h"
#include "mainfrm.h"
#include <propkey.h>

#ifdef _DEBUG
#define new DEBUG_NEW
#endif

// ComokDoc

IMPLEMENT_DYNCREATE(ComokDoc, CDocument)

BEGIN_MESSAGE_MAP(ComokDoc, CDocument)
END_MESSAGE_MAP()


// ComokDoc 생성/소멸

ComokDoc::ComokDoc() noexcept
{
	// TODO: 여기에 일회성 생성 코드를 추가합니다.
	for (int row = 0; row < 19; row++)
		for (int col = 0; col < 19; col++)
			baduk[row][col] = 0;
}

ComokDoc::~ComokDoc()
{
}

BOOL ComokDoc::OnNewDocument()
{
	if (!CDocument::OnNewDocument())
		return FALSE;

	// TODO: 여기에 재초기화 코드를 추가합니다.
	// SDI 문서는 이 문서를 다시 사용합니다.

	return TRUE;
}




// ComokDoc serialization

void ComokDoc::Serialize(CArchive& ar)
{
	if (ar.IsStoring())
	{
		// TODO: 여기에 저장 코드를 추가합니다.
		for (int row = 0; row < 19; row++)
			for (int col = 0; col < 19; col++)
				ar << baduk[row][col];
	}
	else
	{
		// TODO: 여기에 로딩 코드를 추가합니다.
		for (int row = 0; row < 19; row++)
			for (int col = 0; col < 19; col++)
				ar >> baduk[row][col];
	}
}

#ifdef SHARED_HANDLERS

// 축소판 그림을 지원합니다.
void ComokDoc::OnDrawThumbnail(CDC& dc, LPRECT lprcBounds)
{
	// 문서의 데이터를 그리려면 이 코드를 수정하십시오.
	dc.FillSolidRect(lprcBounds, RGB(255, 255, 255));

	CString strText = _T("TODO: implement thumbnail drawing here");
	LOGFONT lf;

	CFont* pDefaultGUIFont = CFont::FromHandle((HFONT) GetStockObject(DEFAULT_GUI_FONT));
	pDefaultGUIFont->GetLogFont(&lf);
	lf.lfHeight = 36;

	CFont fontDraw;
	fontDraw.CreateFontIndirect(&lf);

	CFont* pOldFont = dc.SelectObject(&fontDraw);
	dc.DrawText(strText, lprcBounds, DT_CENTER | DT_WORDBREAK);
	dc.SelectObject(pOldFont);
}

// 검색 처리기를 지원합니다.
void ComokDoc::InitializeSearchContent()
{
	CString strSearchContent;
	// 문서의 데이터에서 검색 콘텐츠를 설정합니다.
	// 콘텐츠 부분은 ";"로 구분되어야 합니다.

	// 예: strSearchContent = _T("point;rectangle;circle;ole object;");
	SetSearchContent(strSearchContent);
}

void ComokDoc::SetSearchContent(const CString& value)
{
	if (value.IsEmpty())
	{
		RemoveChunk(PKEY_Search_Contents.fmtid, PKEY_Search_Contents.pid);
	}
	else
	{
		CMFCFilterChunkValueImpl *pChunk = nullptr;
		ATLTRY(pChunk = new CMFCFilterChunkValueImpl);
		if (pChunk != nullptr)
		{
			pChunk->SetTextValue(PKEY_Search_Contents, value, CHUNK_TEXT);
			SetChunkValue(pChunk);
		}
	}
}

#endif // SHARED_HANDLERS

// ComokDoc 진단

#ifdef _DEBUG
void ComokDoc::AssertValid() const
{
	CDocument::AssertValid();
}

void ComokDoc::Dump(CDumpContext& dc) const
{
	CDocument::Dump(dc);
}
#endif //_DEBUG


// ComokDoc 명령


BOOL ComokDoc::OnSaveDocument(LPCTSTR lpszPathName)
{
	// TODO: 여기에 특수화된 코드를 추가 및/또는 기본 클래스를 호출합니다.

	return CDocument::OnSaveDocument(lpszPathName);
}


BOOL ComokDoc::OnOpenDocument(LPCTSTR lpszPathName)
{
	if (!CDocument::OnOpenDocument(lpszPathName))
		return FALSE;

	// TODO:  여기에 특수화된 작성 코드를 추가합니다.
	CMainFrame* p = (CMainFrame*)AfxGetMainWnd();
	ComokView* pView = (ComokView*)p->GetActiveView();
	int dol_cnt = 0;

	for (int row = 0; row < 19; row++)
		for (int col = 0; col < 19; col++)
			if (baduk[row][col] > 0) dol_cnt++;

	if (dol_cnt % 2 == 0) pView->m_sunseo = true;
	else pView->m_sunseo = false;

	pView->m_row = -1;
	pView->m_col = -1;

	pView->Invalidate(true);

	return TRUE;
}
