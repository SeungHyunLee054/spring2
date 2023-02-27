
// omokView.cpp: ComokView 클래스의 구현
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

#ifdef _DEBUG
#define new DEBUG_NEW
#endif


// ComokView

IMPLEMENT_DYNCREATE(ComokView, CView)

BEGIN_MESSAGE_MAP(ComokView, CView)
	// 표준 인쇄 명령입니다.
	ON_COMMAND(ID_FILE_PRINT, &CView::OnFilePrint)
	ON_COMMAND(ID_FILE_PRINT_DIRECT, &CView::OnFilePrint)
	ON_COMMAND(ID_FILE_PRINT_PREVIEW, &CView::OnFilePrintPreview)
	ON_WM_LBUTTONDOWN()
	ON_COMMAND(ID_EDIT_UNDO, &ComokView::OnEditUndo)
END_MESSAGE_MAP()

// ComokView 생성/소멸

ComokView::ComokView() noexcept
{
	// TODO: 여기에 생성 코드를 추가합니다.
	m_sunseo = true;
	m_row = -1;
	m_col = -1;
	m_bwin = 0;
	m_wwin = 0;
}

ComokView::~ComokView()
{
}

BOOL ComokView::PreCreateWindow(CREATESTRUCT& cs)
{
	// TODO: CREATESTRUCT cs를 수정하여 여기에서
	//  Window 클래스 또는 스타일을 수정합니다.

	return CView::PreCreateWindow(cs);
}

// ComokView 그리기

void ComokView::OnDraw(CDC* pDC)
{
	ComokDoc* pDoc = GetDocument();
	ASSERT_VALID(pDoc);
	if (!pDoc)
		return;

	// TODO: 여기에 원시 데이터에 대한 그리기 코드를 추가합니다.
	CBrush blackbrush, whitebrush, yellowbrush, * pold;
	int row, col;
	int x, y, px, py, ex, ey;
	int start_x = 50, start_y = 50;
	int width = 40;

	px = start_x;
	py = start_y;
	ex = start_x + width * 18;
	ey = start_y + width * 18;

	yellowbrush.CreateSolidBrush(RGB(255, 128, 0));
	blackbrush.CreateSolidBrush(RGB(0, 0, 0));
	whitebrush.CreateSolidBrush(RGB(255, 255, 255));

	//pold = pDC->SelectObject(&yellowbrush);
	//pDC->Rectangle(px, py, ex, ey);
	//pDC->SelectObject(pold);
	CBitmap bit, * poldbit;
	CDC memDC;
	bit.LoadBitmapW(IDB_BITMAP1);
	memDC.CreateCompatibleDC(pDC);
	poldbit = memDC.SelectObject(&bit);
	pDC->StretchBlt(start_x - 20, start_y - 20, width * 18 + 40, width * 18 + 40, &memDC, 0, 0, 512, 512, SRCCOPY);
	memDC.SelectObject(poldbit);

	for (x = 0; x < 19; x++) {
		px = start_x + x * width;
		py = start_y;
		ex = px;
		ey = py + width * 18;
		pDC->MoveTo(px, py);
		pDC->LineTo(ex, ey);
	}
	for (y = 0; y < 19; y++) {
		px = start_x;
		py = start_y + y * width;
		ex = start_x + width * 18;
		ey = py;
		pDC->MoveTo(px, py);
		pDC->LineTo(ex, ey);
	}

	for (row = 0; row < 19; row++) {
		for (col = 0; col < 19; col++) {
			if (pDoc->baduk[row][col] == 1) {
				px = start_x + width * col - width / 2;
				py = start_y + width * row - width / 2;
				ex = px + width;
				ey = py + width;
				pold = pDC->SelectObject(&blackbrush);
				pDC->Ellipse(px, py, ex, ey);
				pDC->SelectObject(pold);
			}
			if (pDoc->baduk[row][col] == 2) {
				px = start_x + width * col - width / 2;
				py = start_y + width * row - width / 2;
				ex = px + width;
				ey = py + width;
				pold = pDC->SelectObject(&whitebrush);
				pDC->Ellipse(px, py, ex, ey);
				pDC->SelectObject(pold);
			}
		}
	}

	yellowbrush.DeleteObject();
	blackbrush.DeleteObject();
	whitebrush.DeleteObject();

	/*CString buf;
	buf.Format(_T("흑의 전적: %d전 %d승 %패"), (m_bwin + m_wwin), m_bwin, m_wwin);
	pDC->TextOutW(start_x + width * 18 + 50, start_y, buf);
	buf.Format(_T("백의 전적: %d전 %d승 %패"), m_bwin + m_wwin, m_wwin, m_bwin);
	pDC->TextOutW(start_x + width * 18 + 50, start_y + 30, buf);*/
}


// ComokView 인쇄

BOOL ComokView::OnPreparePrinting(CPrintInfo* pInfo)
{
	// 기본적인 준비
	return DoPreparePrinting(pInfo);
}

void ComokView::OnBeginPrinting(CDC* /*pDC*/, CPrintInfo* /*pInfo*/)
{
	// TODO: 인쇄하기 전에 추가 초기화 작업을 추가합니다.
}

void ComokView::OnEndPrinting(CDC* /*pDC*/, CPrintInfo* /*pInfo*/)
{
	// TODO: 인쇄 후 정리 작업을 추가합니다.
}


// ComokView 진단

#ifdef _DEBUG
void ComokView::AssertValid() const
{
	CView::AssertValid();
}

void ComokView::Dump(CDumpContext& dc) const
{
	CView::Dump(dc);
}

ComokDoc* ComokView::GetDocument() const // 디버그되지 않은 버전은 인라인으로 지정됩니다.
{
	ASSERT(m_pDocument->IsKindOf(RUNTIME_CLASS(ComokDoc)));
	return (ComokDoc*)m_pDocument;
}
#endif //_DEBUG


// ComokView 메시지 처리기


void ComokView::OnLButtonDown(UINT nFlags, CPoint point)
{
	// TODO: 여기에 메시지 처리기 코드를 추가 및/또는 기본값을 호출합니다.
	int sx, sy, ex, ey, col, row;
	double fx, fy;
	int width, kan;
	width = 40, kan = 18;

	ComokDoc* pDoc = GetDocument();

	sx = 50, sy = 50;
	ex = sx + width * kan;
	ey = sy + width * kan;

	if (point.x<sx || point.x>ex || point.y<sy || point.y>ey)
		return;

	col = (point.x - sx) / width;
	row = (point.y - sy) / width;

	fx = double((point.x - sx)) / double(width);
	fy = double((point.y - sy)) / double(width);
	if (fx - col > 0.5) col++;
	if (fy - row > 0.5) row++;

	if (pDoc->baduk[row][col] == 0) {
		if (m_sunseo) {
			pDoc->baduk[row][col] = 1;
			m_sunseo = false;
		}
		else {
			pDoc->baduk[row][col] = 2;
			m_sunseo = true;
		}

		Invalidate(false);

		m_row = row;
		m_col = col;

		if (m_sunseo) {
			if (CheckVictory(2, row, col) == 1) {
				AfxMessageBox(_T("백이 승리했습니다. 새게임을 시작합니다."), MB_OK);
				for (int row = 0; row < 18; row++)
					for (int col = 0; col < 18; col++)
						pDoc->baduk[row][col] = 0;
				m_sunseo = true;
				m_wwin++;
				m_row = -1;
				m_col = -1;
				Invalidate(true);
			}
		}
		else {
			if (CheckVictory(1, row, col) == 1) {
				AfxMessageBox(_T("흑이 승리했습니다. 새게임을 시작합니다."), MB_OK);
				for (int row = 0; row < 18; row++)
					for (int col = 0; col < 18; col++)
						pDoc->baduk[row][col] = 0;
				m_sunseo = true;
				m_bwin++;
				m_row = -1;
				m_col = -1;
				Invalidate(true);
			}
		}
	}

	CView::OnLButtonDown(nFlags, point);
}


int ComokView::CheckVictory(int a, int r, int c)
{
	// TODO: 여기에 구현 코드 추가.
	ComokDoc* pDoc = GetDocument();
	int i;

	int count = 0;
	for (i = 0; i < 19; i++) {
		if (pDoc->baduk[r][i] == a) count++;
		else count = 0;

		if (count == 5) return 1;
	}

	count = 0;
	for (i = 0; i < 19; i++) {
		if (pDoc->baduk[i][c] == a) count++;
		else count = 0;

		if (count == 5) return 1;
	}

	int srow, scol;
	int erow, ecol;
	int tr, tc;
	tr = r; tc = c;

	while (tr != 0 && tc != 0) {
		tr--;
		tc--;
	}

	srow = tr; scol = tc;

	tr = r; tc = c;
	while (tr != 18 && tc != 18) {
		tr++;
		tc++;
	}

	erow = tr; ecol = tc;
	count = 0;

	while (scol <= ecol && srow <= erow) {
		if (pDoc->baduk[srow][scol] == a) count++;
		else count = 0;

		if (count == 5) return 1;
		srow++;
		scol++;
	}

	tr = r; tc = c;

	while (tr != 0 && tc != 18) {
		tr--;
		tc++;
	}

	srow = tr; scol = tc;

	tr = r; tc = c;
	while (tr != 18 && tc != 0) {
		tr++;
		tc--;
	}

	erow = tr; ecol = tc;

	count = 0;

	while (scol >= ecol && srow <= erow) {
		if (pDoc->baduk[srow][scol] == a) count++;
		else count = 0;

		if (count == 5) return 1;
		srow++;
		scol--;
	}

	return 0;
}


void ComokView::OnEditUndo()
{
	// TODO: 여기에 명령 처리기 코드를 추가합니다.
	ComokDoc* pDoc = GetDocument();

	if (m_row < 0 || m_col < 0) {
		AfxMessageBox(_T("취소 불가능"));
		return;
	}

	pDoc->baduk[m_row][m_col] = 0;

	if (m_sunseo) m_sunseo = false;
	else m_sunseo = true;

	Invalidate(true);
}
